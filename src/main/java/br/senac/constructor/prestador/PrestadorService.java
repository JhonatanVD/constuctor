package br.senac.constructor.prestador;

import br.senac.constructor.usuario.Usuario;
import br.senac.constructor.usuario.UsuarioService;
import br.senac.constructor.exception.NotFoundException;
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
    private UsuarioService usuarioService;

    public Prestador criarPrestador(PrestadorRepresentation.CriarOuAtualizar criar){
        Usuario usuario = this.usuarioService.buscarUmUsuario(criar.getUsuario());
        return this.prestadorRepository.save(Prestador.builder()
                        .contato(criar.getContato())
                        .documento(criar.getDocumento())
                        .usuario(usuario)
                        .status(StatusEnum.ATIVO)
                        .build());
    }
    public Page<Prestador> buscarTodos(Pageable pageable){
        return this.prestadorRepository.findAll(pageable);
    }
    public Page<Prestador> buscarTodos(Predicate filtroURI, Pageable pageable){
        return this.prestadorRepository.findAll(pageable);
    }
    public Prestador atualizar(Long idPrestador, PrestadorRepresentation.CriarOuAtualizar atualizar){
        Prestador oldPrestador = this.getPrestador(idPrestador);

        Prestador newPrestador = oldPrestador.toBuilder()
                .contato(atualizar.getContato())
                .documento(atualizar.getDocumento())
                .status(atualizar.getStatus())
                .build();

        return this.prestadorRepository.save(newPrestador);
    }
    public Prestador buscarUmPrestador(Long idPrestador){
        return this.getPrestador(idPrestador);
    }
    private Prestador getPrestador(Long idPrestador) {
        Optional<Prestador> prestadorAtual = this.prestadorRepository.findById(idPrestador);
        if (prestadorAtual.isPresent()) {
            return prestadorAtual.get();
        } else {
            throw new NotFoundException("Prestador não encontrado");
        }
    }
    public void excluir(Long id){
        Prestador prestador = this.getPrestador(id);
        prestador.setStatus(StatusEnum.INATIVO);
        this.prestadorRepository.save(prestador);
    }
}


