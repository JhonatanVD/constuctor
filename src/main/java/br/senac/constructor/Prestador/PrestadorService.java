package br.senac.constructor.Prestador;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PrestadorService {

    private PrestadorRepository prestadorRepository;

    public Prestador criarPrestador(PrestadorRepresentation.CriarOuAtualizar criar){
        return this.prestadorRepository.save(Prestador.builder()
                        .contato(criar.getContato())
                        .cpf(criar.getCpf())
                        .build());
    }
}


