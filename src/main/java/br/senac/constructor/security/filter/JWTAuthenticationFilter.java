package br.senac.constructor.security.filter;

import br.senac.constructor.exception.JWTAuthenticationException;
import br.senac.constructor.exception.ResponseErro;
import br.senac.constructor.exception.RestExceptionHandler;
import br.senac.constructor.security.token.TokenStructure;
import br.senac.constructor.security.token.TokenValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final RestExceptionHandler restExceptionHandler;
    private final ObjectMapper objectMapper;
    private final TokenValidation tokenValidation;

    private Boolean checkAllowedPatchs(HttpServletRequest request){
        return (request.getRequestURI().contains("/api/login") || request.getRequestURI().contains("/api/usuario"));
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (!checkAllowedPatchs(request)) {
            try {
                String HEADER_STRING = "Authorization";

                String bearerToken = Optional.ofNullable(request.getHeader(HEADER_STRING)).orElseThrow(() -> new JWTAuthenticationException("Token nao encontrado no header"));
                TokenStructure tokenStructure = tokenValidation.buscarTokenData(bearerToken);
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(
                                new UsernamePasswordAuthenticationToken(tokenStructure, null,
                                        Collections.emptyList()));
                filterChain.doFilter(request, response);
            } catch (JWTAuthenticationException ex) {
                ResponseErro errorPayload = restExceptionHandler.handleJWTAuthenticationException(ex);
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(objectMapper.writeValueAsString(errorPayload));
            }
        } else {
                filterChain.doFilter(request, response);
            }
        }
    }