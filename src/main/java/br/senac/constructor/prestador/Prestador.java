package br.senac.constructor.prestador;


import br.senac.constructor.exception.BusinessException;
import br.senac.constructor.usuario.Usuario;
import br.senac.constructor.utils.StatusEnum;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prestador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo CPF não pode ser nulo!")
    @Column(name = "documento")
    private String documento;

    @Column(name = "contato")
    @NotEmpty(message = "O contato do prestador não pode ser nulo!")
    private String contato;

    @NotNull(message = "O campo status não pode ser nulo")
    @Column(name= "status")
    private StatusEnum status;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Builder(toBuilder = true)
    public Prestador(String documento, String contato, StatusEnum status, Usuario usuario) {
        if (documento.replace(" ", "").length() != 14 && documento.replace(" ", "").length() != 11) {
            throw new BusinessException("Documento do prestador invalido");
        }
        this.documento = documento;
        this.contato = contato;
        this.status = status;
        this.usuario = usuario;
    }
}
