package br.senac.constructor.prestadorServico;


import br.senac.constructor.prestadorServico.utils.Tipo_servico;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prestador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestadorServico")
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "contato")
    private String contato;


}
