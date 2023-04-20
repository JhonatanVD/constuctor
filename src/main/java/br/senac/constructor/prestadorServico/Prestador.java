package br.senac.constructor.prestadorServico;


import lombok.*;


import javax.persistence.*;

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
