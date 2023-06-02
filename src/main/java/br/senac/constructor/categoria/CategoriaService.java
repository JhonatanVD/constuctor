package br.senac.constructor.categoria;

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
public class CategoriaService {


    private CategoriaRepository categoriaRepository;
    public Categoria criarCategoria(CategoriaRepresentation.CriarOuAtualizar criar){

        return this.categoriaRepository.save(Categoria.builder()
                        .categoria(criar.getCategoria())
                .build());
    }
    public Page<Categoria> buscarTodos(Pageable pageable) {
        return this.categoriaRepository.findAll(pageable);
    }
    public Page<Categoria> buscarTodos(Predicate filtroURI, Pageable  pageable){
        return this.categoriaRepository.findAll(pageable);
    }
    public Categoria atualizar(Long idCategoria, CategoriaRepresentation.CriarOuAtualizar atualizar){
        Categoria categoriaParaAtualizar = Categoria.builder()
                .categoria(atualizar.getCategoria())
                .build();
        return this.categoriaRepository.save(categoriaParaAtualizar);
    }
    public Categoria buscarUmaCategoria(Long idCategoria) {
        Optional<Categoria> categoriaAtual = this.categoriaRepository.findById(idCategoria);
        if(categoriaAtual.isPresent()){
            return categoriaAtual.get();
        }else
            throw new NotFoundException("Categoria não encontrada");
    }
    public void deleteCategoria(Long idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }
}
