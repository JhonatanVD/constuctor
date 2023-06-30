package br.senac.constructor.file;

import br.senac.constructor.servico.Servico;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "file")
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "arquivo", nullable = false)
    private byte[] file;

    @Column(name = "nome_arquivo", nullable = false)
    private String fileName;

    @Column(name = "tipo_arquivo", nullable = false)
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "id_servico", nullable = false)
    private Servico servico;
}
