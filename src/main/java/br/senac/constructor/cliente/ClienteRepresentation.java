package br.senac.constructor.cliente;

import br.senac.constructor.utils.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface ClienteRepresentation {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class CriarOuAtualizar{

        @NotEmpty(message = "O contato do cliente não pode ser nulo!")
        private String contato;

        @NotEmpty(message = "O documento do cliente é nao pode ser nulo")
        private String documento;

        private StatusEnum status;

        @NotNull(message = "O usuario do cliente nao pode ser nulo")
        private Long usuario;

    }
    @Data
    @Builder
    class Detalhes{
        private String contato;
        private String documento;
        public static ClienteRepresentation.Detalhes from(Cliente cliente){
        return Detalhes.builder()
                .contato(cliente.getContato())
                .documento(cliente.getDocumento())
                .build();
        }
    }
}
