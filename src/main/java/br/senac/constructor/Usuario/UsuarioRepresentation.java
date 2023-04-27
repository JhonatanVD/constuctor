package br.senac.constructor.Usuario;

import br.senac.constructor.permissao.Permissao;
import br.senac.constructor.permissao.PermissaoRepresentation;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public interface UsuarioRepresentation {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar{

        @NotNull(message = " O nome não pode ser nulo!")
        @NotEmpty(message = "O nome não pode ser vazio!")
        private String nome;

        @Email(message = "Email inválido")
        private String Email;

        @Size(min = 6, max = 20, message = "Confirmar senha é obrigatoŕio!")
        private String senha;
        private String confirmarSenha;
        private String status;
        private String criadoEM;
        private String attEM;
    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String nome;
        private String senha;
        private String confirmarSenha;
        private String status;
        private String criadoEM;
        private String attEM;

        public static UsuarioRepresentation.Detalhes from(Usuario usuario) {
            return Detalhes.builder()
                    .id(usuario.getId())
                    .nome(usuario.getNome())
                    .senha(usuario.getSenha())
                    .confirmarSenha(usuario.getConfirmarSenha())
                    .status(usuario.getStatus())
                    .criadoEM(usuario.getCriadoEm())
                    .attEM(usuario.getAttEm())
                    .build();
        }
    }
    @Data
    @Builder
    class Lista{
        private Long id;
        private String nome;
        private String senha;
        private String confirmarSenha;
        private String status;
        private String criadoEM;
        private String attEM;
        private static UsuarioRepresentation.Lista from(Usuario usuario){
            return UsuarioRepresentation.Lista.builder()
                    .id(usuario.getId())
                    .nome(usuario.getNome())
                    .senha(usuario.getSenha())
                    .confirmarSenha(usuario.getConfirmarSenha())
                    .status(usuario.getStatus())
                    .criadoEM(usuario.getCriadoEm())
                    .attEM(usuario.getAttEm())
                    .build();

        }
        public static List<Lista> from(List<Usuario> UsuarioList){
            return UsuarioList
                    .stream()
                    .map(Lista::from)
                    .collect(Collectors.toList());
        }
    }

    @Data
    @Builder
    class Excluir{
        private Long id;
        public static UsuarioRepresentation.Excluir from(Usuario usuario){
            return Excluir.builder()
                    .id(usuario.getId())
                    .build();
        }
    }
}
