package br.senac.constructor.permissao;

import br.senac.constructor.exception.NotFoundException;
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
public class PermissaoService{

    private PermissaoRepository permissaoRepository;

    public Permissao criarPermissao(PermissaoRepresentation.CriarOuAtualizar criar){
        return this.permissaoRepository.save(Permissao.builder()
                .nome(criar.getNome())
                .build());
    }

    public Page<Permissao> buscarTodos(Pageable pageable) {

        return this.permissaoRepository.findAll(pageable);
    }
    public Page<Permissao> buscarTodos(Predicate filtroURI , Pageable pageable){
        return this.permissaoRepository.findAll(pageable);
    }

    public Permissao atualizar(Long idPermissao, PermissaoRepresentation.CriarOuAtualizar atualizar){
        Permissao permissaoParaAtualizar = Permissao.builder()
                .id(idPermissao)
                .nome(atualizar.getNome())
                .build();

        return this.permissaoRepository.save(permissaoParaAtualizar);
    }
    public Permissao buscarUmaPermissao(Long idPermissao){
        return this.getPermissao(idPermissao);
    }
    private Permissao getPermissao(Long idPermissao){
        Optional<Permissao> permissaoAtual = this.permissaoRepository.findById(idPermissao);
        if(permissaoAtual.isPresent()){
            return permissaoAtual.get();
        }else {
            throw new NotFoundException("Permissão não encontrada");
        }
    }
}
