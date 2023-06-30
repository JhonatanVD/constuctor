package br.senac.constructor.usuario;


import br.senac.constructor.permissao.Permissao;
import br.senac.constructor.utils.StatusEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "usuario")
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    @NotNull(message = "O nome não pode ser nulo!")
    @NotEmpty(message = "O nome não pode ser vazio!")
    private String nome;

    @Column(name= "email")
    @Email(message = "Email invalido!")
    @NotEmpty(message = "O email não pode ser nulo")
    private String email;

    @Column(name= "senha")
    @Size(min = 6, message = "A senha deve ter no minimo 6 caracteres")
    @NotNull(message = "A senha é obrigatória")
    private String senha;

    @Column(name= "confirmarSenha")
    @NotNull(message = "A confirmação de senha é obrigatoria")
    private String confirmarSenha;

    @NotNull(message = "O campo status não pode ser nulo")
    @Column(name= "status")
    private StatusEnum status;

    @Column(name= "criadoEm")
    private LocalDate criadoEm;

    @Column(name= "attEm")
    private LocalDate attEm;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_permissao")
    private Permissao permissao;
}
