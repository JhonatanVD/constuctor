    package br.senac.constructor.clienteServico;
    
    import br.senac.constructor.cliente.Cliente;
    import br.senac.constructor.servico.Servico;
    import lombok.*;
    
    import javax.persistence.*;
    import javax.validation.constraints.NotNull;
    import java.math.BigDecimal;
    import java.sql.Date;
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Setter
    @Getter
    @Entity
    @Table(name = "clienteServico")
    public class ClienteServico {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idClienteServico")
        private Long id;
    
        @Column(name = "valor")
        @NotNull(message = "O valor não pode ser nulo")
        private BigDecimal valor;
    
        @Column(name = "data_servico")
        private Date data;


        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "id_cliente")
        private Cliente cliente;


        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "id_servico")
        private Servico servico;
    }



// criar um tipo (Enum) em usuario