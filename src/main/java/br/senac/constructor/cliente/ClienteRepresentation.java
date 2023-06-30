package br.senac.constructor.cliente;

import br.senac.constructor.usuario.Usuario;
import br.senac.constructor.usuario.UsuarioRepresentation;
import br.senac.constructor.utils.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

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
        private Long id;
        private String contato;
        private String documento;
        private StatusEnum status;
        public static ClienteRepresentation.Detalhes from(Cliente cliente){
        return Detalhes.builder()
                .id(cliente.getId())
                .contato(cliente.getContato())
                .status(cliente.getStatus())
                .documento(cliente.getDocumento())
                .build();
        }
    }
    @Data
    @Builder
    class Lista{
        private String contato;
        private String documento;
        private StatusEnum status;

        private static ClienteRepresentation.Lista from(Cliente cliente){
            return ClienteRepresentation.Lista.builder()
                    .contato(cliente.getContato())
                    .documento(cliente.getDocumento())
                    .status(cliente.getStatus())
                    .build();
        }
        public static List<ClienteRepresentation.Lista> from(List<Cliente> ClienteList){
            return ClienteList
                    .stream()
                    .map(ClienteRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
