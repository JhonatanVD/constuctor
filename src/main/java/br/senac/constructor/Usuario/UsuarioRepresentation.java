package br.senac.constructor.Usuario;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public interface UsuarioRepresentation {



    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar{


        @NotEmpty(message = "O nome é obrigatorio")
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
        class excluir{
            private Long id;
            public static UsuarioRepresentation.Lista from(Usuario usuario){
                return UsuarioRepresentation.Lista.builder()
                        .id(usuario.getId())
                        .build();
        }
   }
}
