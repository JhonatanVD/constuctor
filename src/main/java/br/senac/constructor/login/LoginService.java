package br.senac.constructor.login;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LoginService {

    public LoginRepresentation.Response generateToken(LoginRepresentation.Login login){
        log.info("Email: {} senha: {} ", login.getEmail(), login.getSenha());
        return null;
    }
}
