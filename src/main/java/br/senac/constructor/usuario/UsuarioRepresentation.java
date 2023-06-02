package br.senac.constructor.usuario;


import br.senac.constructor.permissao.PermissaoRepresentation;
import br.senac.constructor.utils.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
        private String email;

        @Size(min = 6, max = 20, message = "Confirmar senha é obrigatoŕio!")
        @NotEmpty(message = "A senha é obrigatorio")
        private String senha;

        @NotEmpty(message = "O confirmar senha é obrigatoria")
        private String confirmarSenha;

        private StatusEnum status;
        private LocalDate attEm;
        private Long permissao;

    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String nome;
        private String senha;
        private String confirmarSenha;
        private StatusEnum status;
        private LocalDate criadoEM;
        private LocalDate attEM;
        private PermissaoRepresentation.Detalhes permissao;

        public static UsuarioRepresentation.Detalhes from(Usuario usuario) {
            return Detalhes.builder()
                    .id(usuario.getId())
                    .nome(usuario.getNome())
                    .senha(usuario.getSenha())
                    .confirmarSenha(usuario.getConfirmarSenha())
                    .status(usuario.getStatus())
                    .criadoEM(usuario.getCriadoEm())
                    .attEM(usuario.getAttEm())
                    .permissao(PermissaoRepresentation.Detalhes.from(usuario.getPermissao()))
                    .build();
        }
    }

    @Data
    @Builder
    class Resumo {
        private Long id;
        private String nome;

        public static UsuarioRepresentation.Resumo from(Usuario usuario) {
            return Resumo.builder()
                    .id(usuario.getId())
                    .nome(usuario.getNome())
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
        private StatusEnum status;
        private LocalDate criadoEM;
        private LocalDate attEM;
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
}