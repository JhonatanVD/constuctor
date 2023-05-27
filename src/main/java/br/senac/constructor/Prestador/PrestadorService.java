package br.senac.constructor.Prestador;

import br.senac.constructor.Usuario.Usuario;
import br.senac.constructor.exceptions.NotFoundException;
import br.senac.constructor.utils.StatusEnum;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Page<Prestador> buscarTodos(Pageable pageable){
        return this.prestadorRepository.findAll(pageable);
    }
    public Page<Prestador> buscarTodos(Predicate filtroURI, Pageable pageable){
        return this.prestadorRepository.findAll(pageable);
    }
    public Prestador atualizar(Long idPrestador, PrestadorRepresentation.CriarOuAtualizar atualizar){
        Prestador pretadorParaAtualizar = Prestador.builder()
                .id(idPrestador)
                .contato(atualizar.getContato())
                .cpf(atualizar.getCpf())
                .build();
        return this.prestadorRepository.save(pretadorParaAtualizar);
    }
    public Prestador buscarUmPrestador(Long idPrestador){
        return this.getPrestador(idPrestador);
    }
    private Prestador getPrestador(Long idPrestador) {
        Optional<Prestador> prestadorAtual = this.prestadorRepository.findById(idPrestador);
        if (prestadorAtual.isPresent()) {
            return prestadorAtual.get();
        } else {
            throw new NotFoundException("Usuário não encontrado");
        }
    }
    public void excluir(Long id){
        Prestador prestador = this.getPrestador(id);
        prestador.setStatus(StatusEnum.INATIVO);
        this.prestadorRepository.save(prestador);
    }
}


