package br.senac.constructor.categoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

public interface CategoriaRepresentation {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarOuAtualizar{

        @NotEmpty(message = "A categoria é obrigatória")
        private String nome;
    }
    @Data
    @Builder
    class Detalhes{

        private Long id;
        private String nome;
        public static CategoriaRepresentation.Detalhes from(Categoria categoria){
            return Detalhes.builder()
                    .id(categoria.getId())
                    .nome(categoria.getNome())
                    .build();
        }
    }
    @Data
    @Builder
    class Lista{
        private Long id;
        private String nome;

        public static CategoriaRepresentation.Lista from(Categoria categoria){
            return CategoriaRepresentation.Lista.builder()
                    .id(categoria.getId())
                    .nome(categoria.getNome())
                    .build();
        }
        public static List<CategoriaRepresentation.Lista> from(List<Categoria> CategoriaList){
            return CategoriaList
                    .stream()
                    .map(Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
