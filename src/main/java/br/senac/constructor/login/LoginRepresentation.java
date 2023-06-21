package br.senac.constructor.login;

import br.senac.constructor.security.token.UserToken;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


public interface LoginRepresentation {

    @Data
    class Login{
        @NotEmpty(message = "O email não pode ser nulo")
        private String email;

        @NotEmpty(message = "A senha não pode ser nulo")
        private String senha;
    }
    @Data
    @Builder
    class Response{
        private UserToken user;
        private String token;
        private String tokenType;
        private String role;
    }

}
