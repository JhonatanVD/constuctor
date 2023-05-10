package br.senac.constructor.Prestador;


import lombok.*;


import javax.persistence.*;
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

    @NotNull(message = "O campo CPF não pode ser nulo!")
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "contato")
    private String contato;


}
