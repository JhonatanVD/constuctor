package br.senac.constructor.Prestador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;

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

    class
}
