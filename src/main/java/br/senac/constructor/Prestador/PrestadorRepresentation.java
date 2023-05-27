package br.senac.constructor.Prestador;

import br.senac.constructor.Usuario.Usuario;
import br.senac.constructor.Usuario.UsuarioRepresentation;
import br.senac.constructor.utils.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

public interface PrestadorRepresentation {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class CriarOuAtualizar{

        private String contato;
        @NotEmpty(message = "O cpf é obrigatório")
        private String cpf;
    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String contato;
        private String cpf;
        private StatusEnum status;

        public static PrestadorRepresentation.Detalhes from(Prestador prestador){
            return Detalhes.builder()
                    .id(prestador.getId())
                    .contato(prestador.getContato())
                    .cpf(prestador.getCpf())
                    .status(StatusEnum.ATIVO)
                    .build();
        }
    }

    @Data
    @Builder
    class Lista{
        private Long id;
        private String contato;
        private String cpf;
        private StatusEnum status;

        private static PrestadorRepresentation.Lista from(Prestador prestador){
            return PrestadorRepresentation.Lista.builder()
                    .id(prestador.getId())
                    .contato(prestador.getContato())
                    .cpf(prestador.getCpf())
                    .status(StatusEnum.ATIVO)
                    .build();
        }
        public static List<PrestadorRepresentation.Lista> from(List<Prestador> PrestadorList){
            return PrestadorList
                    .stream()
                    .map(PrestadorRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }


}
