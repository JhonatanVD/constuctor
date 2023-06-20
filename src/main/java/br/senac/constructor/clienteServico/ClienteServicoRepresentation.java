package br.senac.constructor.clienteServico;

import br.senac.constructor.cliente.ClienteRepresentation;
import br.senac.constructor.servico.ServicoRepresentation;
import br.senac.constructor.utils.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface ClienteServicoRepresentation {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class CriarOuAtualizar{

        @NotNull(message = "O valor não pode ser nulo")
        private BigDecimal valor;
        private LocalDate data;
        private Long servico;
        private Long cliente;
        private StatusEnum status;
    }
    @Data
    @Builder
    class Detalhes{

        private Long id;
        private BigDecimal valor;
        private LocalDate data;
        private StatusEnum status;
        private ClienteRepresentation.Detalhes cliente;
        private ServicoRepresentation.Detalhes servico;
        public static ClienteServicoRepresentation.Detalhes from(ClienteServico clienteServico){
            return Detalhes.builder()
                    .valor(clienteServico.getValor())
                    .data(clienteServico.getData())
                    .status(clienteServico.getStatus())
                    .cliente(ClienteRepresentation.Detalhes.from(clienteServico.getCliente()))
                    .servico(ServicoRepresentation.Detalhes.from(clienteServico.getServico()))
                    .build();
        }
    }
    @Data
    @Builder
    class Lista{
        private Long id;
        private BigDecimal valor;
        private LocalDate data;
        private StatusEnum status;
        private static ClienteServicoRepresentation.Lista from(ClienteServico clienteServico){
            return Lista.builder()
                    .id(clienteServico.getId())
                    .valor(clienteServico.getValor())
                    .status(clienteServico.getStatus())
                    .data(clienteServico.getData())
                    .build();
        }
        public static List<ClienteServicoRepresentation.Lista> from(List<ClienteServico> ClienteServicoList){
            return ClienteServicoList
                    .stream()
                    .map(ClienteServicoRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
}

