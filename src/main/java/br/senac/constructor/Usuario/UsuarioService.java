package br.senac.constructor.Usuario;

import br.senac.constructor.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(UsuarioRepresentation.CriarOuAtualizar criar){
        return this.usuarioRepository.save(Usuario.builder()
                        .nome(criar.getNome())
                        .email(criar.getEmail())
                        .criadoEm(criar.getCriadoEM())
                        .attEm(criar.getAttEM())
                        .senha(criar.getSenha())
                        .confirmarSenha(criar.getConfirmarSenha())
                        .status(criar.getStatus())
                .build());
    }
    public Page<Usuario> buscarTodos(Pageable pageable){
        return this.usuarioRepository.findAll(pageable);
    }
    public Page<Usuario> buscarTodos(Predicate filtroURI, Pageable pageable){
        return this.usuarioRepository.findAll(pageable);
    }
    public Usuario atualizar(Long idUsuario, UsuarioRepresentation.CriarOuAtualizar atualizar){
        Usuario usuarioParaAtualizar = Usuario.builder()
                .id(idUsuario)
                .nome(atualizar.getNome())
                .email(atualizar.getEmail())
                .status(atualizar.getStatus())
                .criadoEm(atualizar.getCriadoEM())
                .attEm(atualizar.getAttEM())
                .senha(atualizar.getSenha())
                .confirmarSenha(atualizar.getConfirmarSenha())
                .build();
        return this.usuarioRepository.save(usuarioParaAtualizar);
    }

    public Usuario buscarUmUsuario(Long idUsuario){
        return this.getUsario(idUsuario);
    }
    private Usuario getUsario(Long idUsuario) {
        Optional<Usuario> usuarioAtual = this.usuarioRepository.findById(idUsuario);
        if (usuarioAtual.isPresent()) {
            return usuarioAtual.get();
        } else {
            throw new NotFoundException("Usuário não encontrada");
        }
    }
}
