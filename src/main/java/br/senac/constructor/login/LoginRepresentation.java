package br.senac.constructor.login;

import br.senac.constructor.usuario.UsuarioRepresentation;
import lombok.*;

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
    class Response{ //retorno do token

        private String token;
        private String role;
        private UsuarioRepresentation.Resumo user;
    }

}
