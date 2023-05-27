package br.senac.constructor.Usuario;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.security.cert.CertPathBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "usuario")
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotNull(message = "O nome não pode ser nulo!")
    @NotEmpty(message = "O nome não pode ser vazio!")
    private String nome;

    @Column(name= "email")
    @Email(message = "Email invalido!")
    private String email;

    @Column(name= "senha")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String senha;


    @Column(name= "confirmarSenha")
    @NotNull(message = "A confirmação de senha é obrigatoria")
    private String confirmarSenha;

    @Column(name= "status")
    private String status;

    @Column(name= "criadoEm")
    private String criadoEm;

    @Column(name= "attEm")
    private String attEm;

}


