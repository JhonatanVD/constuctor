package br.senac.constructor.servico;

import br.senac.constructor.categoria.Categoria;
import br.senac.constructor.prestador.Prestador;
import br.senac.constructor.utils.StatusEnum;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "valorServico")
    @NotNull(message = "O valor não pode ser nulo")
    private Double valorServico;

    @Column(name = "diasTrabalho")
    private String diasTrabalho;

    @NotNull(message = "O campo status não pode ser nulo")
    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "Descricao")
    private String descricao;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prestador")
    private Prestador prestador;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}

