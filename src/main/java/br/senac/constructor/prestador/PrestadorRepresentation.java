package br.senac.constructor.prestador;

import br.senac.constructor.usuario.Usuario;
import br.senac.constructor.usuario.UsuarioRepresentation;
import br.senac.constructor.utils.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public interface PrestadorRepresentation {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class CriarOuAtualizar{

        @NotEmpty(message = "O contato do prestador não pode ser nulo!")
        private String contato;

        @NotEmpty(message = "O documento do prestador é nao pode ser nulo")
        private String documento;

        private StatusEnum status;

        @NotNull(message = "O usuario do prestador nao pode ser null")
        private Long usuario;

    }
    @Data
    @Builder
    class Detalhes {
        private Long id;
        private String contato;
        private String documento;
        private StatusEnum status;
        private UsuarioRepresentation.Resumo usuario;

        public static PrestadorRepresentation.Detalhes from(Prestador prestador){
            return Detalhes.builder()
                    .id(prestador.getId())
                    .contato(prestador.getContato())
                    .documento(prestador.getDocumento())
                    .status(prestador.getStatus())
                    .usuario(UsuarioRepresentation.Resumo.from(prestador.getUsuario()))
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
        private UsuarioRepresentation.Resumo usuario;

        private static PrestadorRepresentation.Lista from(Prestador prestador){
            return Lista.builder()
                    .id(prestador.getId())
                    .contato(prestador.getContato())
                    .cpf(prestador.getDocumento())
                    .status(prestador.getStatus())
                    .usuario(UsuarioRepresentation.Resumo.from(prestador.getUsuario()))
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
