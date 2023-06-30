package br.senac.constructor.cliente;

import br.senac.constructor.usuario.Usuario;
import br.senac.constructor.utils.StatusEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "documento")
    private String documento;

    @Column(name = "contato")
    private String contato;

    @NotNull(message = "O campo status não pode ser nulo")
    @Column(name= "status")
    private StatusEnum status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}

