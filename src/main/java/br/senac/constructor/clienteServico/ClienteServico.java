    package br.senac.constructor.clienteServico;
    
    import lombok.*;
    
    import javax.persistence.*;
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
        private BigDecimal valor;
    
        @Column(name = "data_servico")
        private Date data;

        @Column(name = "Feedback")
        private String feedback;
//
//        @Column(name = "id_cliente")
//        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//        private UserCliente userCliente;

//        @Column(name = "id_servico")
//        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//        private Servico servico;

    }

// criar um tipo (Enum) em usuario