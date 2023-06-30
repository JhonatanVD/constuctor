package br.senac.constructor.security.config;

import br.senac.constructor.exception.RestExceptionHandler;
import br.senac.constructor.security.token.TokenValidation;
import br.senac.constructor.security.filter.JWTAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final RestExceptionHandler restExceptionHandler;
    private final ObjectMapper objectMapper;
    private final TokenValidation tokenValidation;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .configurationSource(request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedOrigins(Arrays.asList("*"));
                    cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                    cors.setAllowedHeaders(Arrays.asList("*"));
                    return cors;
                })
                .and()
                .csrf().disable()
                .headers()
                .frameOptions().disable()
                .and()
                .addFilterBefore(new JWTAuthenticationFilter(restExceptionHandler, objectMapper, this.tokenValidation), UsernamePasswordAuthenticationFilter.class);
    }
}