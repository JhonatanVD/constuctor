package br.senac.constructor.permissao;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoRepresentation{

    class CriarOuAtualizar{
        @NotNull(message = " O nome não pode ser nulo!")
        @NotEmpty(message = "O nome não pode ser vazio!" )
        private String nome;

    }
    @Data
    @Builder
    class Dettalhes {
        private Long id;
        private String nome;

        private static PermissaoRepresentation.Dettalhes from(Permissao permissao) {
            return PermissaoRepresentation.Dettalhes.builder()
                    .id(permissao.getId())
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
                return Lista.builder()
                        .id(permissao.getId())
                        .nome(permissao.getNome())
                        .build();
            }

        }

}
