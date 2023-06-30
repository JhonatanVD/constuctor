package br.senac.constructor.login;


import br.senac.constructor.exception.UnauthorizedException;
import br.senac.constructor.security.token.TokenService;
import br.senac.constructor.security.token.UserToken;
import br.senac.constructor.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LoginService {
    private final TokenService tokenService;
    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder encoder;

    public LoginRepresentation.Response generateToken(LoginRepresentation.Login login){
        var user = usuarioService.buscarPorEmail(login.getEmail());

        if (!this.encoder.matches(login.getSenha(), user.getSenha())) {
            throw new UnauthorizedException("A Senha informada está inválida");
        }

        var role = user.getPermissao().getNome();
        var token = this.tokenService.generateToken(new UserToken(user.getId(), user.getEmail()), role);

        return LoginRepresentation.Response.builder()
                .user(UserToken.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .build())
                .token(token)
                .role(role)
                .tokenType("Bearer")
                .build();
    }
}
