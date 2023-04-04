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

    @Column(name="nome")
    @NotNull(message = "O nome não pode ser nulo")
    @NotNull(message = "O nome não pode ser nulo")
    @NotEmpty(message= "O nome não pode ser vazio")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "contato")
    private String contato;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "tipo_servico")
    @Enumerated(EnumType.STRING)
    private Tipo_servico tipo_servico;

    @Column(name = "dias_trabalho")
    private String dias_trabalho;

    @Column(name = "endereco")
    private String endereco;


}
