package br.senac.constructor.usuario;

import br.senac.constructor.exception.BusinessException;
import br.senac.constructor.exception.NotFoundException;
import br.senac.constructor.permissao.Permissao;
import br.senac.constructor.permissao.PermissaoService;
import br.senac.constructor.utils.StatusEnum;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private PermissaoService permissaoService;
    private BCryptPasswordEncoder encoder;

    public Usuario criarUsuario(UsuarioRepresentation.CriarOuAtualizar criar){
        Permissao permissao = this.permissaoService.buscarUmaPermissao(criar.getPermissao());
        this.userAlreadyExists(criar.getEmail()); // valida email já existente

        return this.usuarioRepository.save(Usuario.builder()
                        .nome(criar.getNome())
                        .email(criar.getEmail())
                        .criadoEm(LocalDate.now())
                        .senha(encoder.encode(criar.getSenha()))
                        .confirmarSenha(encoder.encode(criar.getConfirmarSenha()))
                        .status(StatusEnum.ATIVO)
                        .permissao(permissao)
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
                .senha(this.encoder.encode(atualizar.getSenha()))
                .confirmarSenha(this.encoder.encode(atualizar.getConfirmarSenha()))
                .attEm(atualizar.getAttEm())
                .build();
        return this.usuarioRepository.save(usuarioParaAtualizar);
    }
    public Usuario buscarUmUsuario(Long idUsuario){
        Optional<Usuario> usuarioAtual = this.usuarioRepository.findById(idUsuario);
        if (usuarioAtual.isPresent()) {
            return usuarioAtual.get();
        } else {
            throw new NotFoundException("Usuário não encontrado");
        }
    }

    public void excluir(Long id){
        Usuario usuario = this.buscarUmUsuario(id);
        usuario.setStatus(StatusEnum.INATIVO);
        this.usuarioRepository.save(usuario);
    }

    public void userAlreadyExists(String email){
        if (this.usuarioRepository.existsUserByEmail(email)) {
           throw new BusinessException("O e-mail %s já esta sendo ultilizado".formatted(email));
        }
    }

    public Usuario buscarPorEmail(String email) {
        return this.usuarioRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Nenhum usuario cadastrado com o email %s".formatted(email)));
    }
}
