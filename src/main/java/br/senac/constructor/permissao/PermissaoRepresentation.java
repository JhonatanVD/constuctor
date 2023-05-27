package br.senac.constructor.permissao;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


public interface PermissaoRepresentation{

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar{
        @NotNull(message = " O nome não pode ser nulo!")
        @NotEmpty(message = "O nome não pode ser vazio!")
        private String nome;
    }
    @Data
    @Builder
    class Detalhes {
        private String nome;

        public static PermissaoRepresentation.Detalhes from(Permissao permissao) {
            return PermissaoRepresentation.Detalhes.builder()
                    .nome(permissao.getNome())
                    .build();
        }
    }
    @Data
    @Builder
    class Lista{
        private Long id;
        private String nome;
        private static PermissaoRepresentation.Lista from(Permissao permissao){
            return PermissaoRepresentation.Lista.builder()
                    .id(permissao.getId())
                    .nome(permissao.getNome())
                    .build();
        }

        public static List<Lista> from(List<Permissao> permissoes){
            return permissoes
                    .stream()
                    .map(Lista::from)
                    .collect(Collectors.toList());
        }
    }
}




