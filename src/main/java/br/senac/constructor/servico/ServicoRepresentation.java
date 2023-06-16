package br.senac.constructor.servico;

import br.senac.constructor.categoria.CategoriaRepresentation;
import br.senac.constructor.permissao.PermissaoRepresentation;
import br.senac.constructor.prestador.PrestadorRepresentation;

import br.senac.constructor.utils.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


public interface ServicoRepresentation {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    class CriarOuAtualizar{

        @NotNull(message = "O valor não pode ser nulo")
        private Double valorServico;

        private String diasTrabalho;

        @NotNull(message = "O campo status não pode ser nulo")
        private StatusEnum status;
        private String descricao;
        private String endereco;
        private Long categoria;
        private Long prestador;
    }
    @Data
    @Builder
    class Detalhes{

        private Long id;
        private Double valorServico;
        private String diasTrabalho;
        private StatusEnum status;
        private String descricao;
        private String endereco;
        private PrestadorRepresentation.Detalhes prestador;
        private CategoriaRepresentation.Detalhes categoria;

        public static ServicoRepresentation.Detalhes from(Servico servico) {
            return Detalhes.builder()
                    .id(servico.getId())
                    .valorServico(servico.getValorServico())
                    .diasTrabalho(servico.getDiasTrabalho())
                    .status(StatusEnum.ATIVO)
                    .descricao(servico.getDescricao())
                    .endereco(servico.getEndereco())
                    .prestador(PrestadorRepresentation.Detalhes.from(servico.getPrestador()))
                    .categoria(CategoriaRepresentation.Detalhes.from(servico.getCategoria()))
                    .build();
        }
    }
    @Data
    @Builder
    class Lista{
        private Long id;
        private Double valorServico;
        private String diasTrabalho;
        private StatusEnum status;
        private String descricao;
        private String endereco;

       private static ServicoRepresentation.Lista from(Servico servico){
           return Lista.builder()
                   .id(servico.getId())
                   .descricao(servico.getDescricao())
                   .diasTrabalho(servico.getDiasTrabalho())
                   .endereco(servico.getEndereco())
                   .status(servico.getStatus())
                   .valorServico(servico.getValorServico())
                   .build();
       }
       public static  List<ServicoRepresentation.Lista> from(List<Servico> ServicoList){
           return ServicoList
                   .stream()
                   .map(ServicoRepresentation.Lista::from)
                   .collect(Collectors.toList());
       }
    }
}
