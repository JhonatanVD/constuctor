package br.senac.constructor.categoria;

import br.senac.constructor.utils.Paginacao;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin("*")
@AllArgsConstructor
public class CategoriaController {

    private CategoriaService categoriaService;
    @PostMapping("/")
    public ResponseEntity<CategoriaRepresentation.Detalhes> criarCategoria
            (@RequestBody @Valid CategoriaRepresentation.CriarOuAtualizar criar){

        Categoria categoria = this.categoriaService.criarCategoria(criar);

        CategoriaRepresentation.Detalhes detalhes = CategoriaRepresentation.Detalhes.from(categoria);
        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/all1")
    public ResponseEntity<Paginacao> buscarCategoria(
            @QuerydslPredicate(root = Categoria.class) Predicate filtroURI,
            @RequestParam(name = "tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Categoria> categoriaList = Objects.isNull(filtroURI)?
                this.categoriaService.buscarTodos(pageable):
                this.categoriaService.buscarTodos(filtroURI, pageable);

        Paginacao paginacao = Paginacao.builder()
                .paginaSelecionada(paginaSelecionada)
                .tamanhoPagina(tamanhoPagina)
                .totalRegistros(categoriaList.getTotalElements())
                .proximaPagina(categoriaList.hasNext())
                .conteudo(CategoriaRepresentation.Lista
                        .from(categoriaList.getContent()))
                .build();
        return ResponseEntity.ok(paginacao);
    }
    @PutMapping("/{idCategoria}")
    public ResponseEntity<CategoriaRepresentation.Detalhes> atualizarCategoria(@PathVariable Long idCategoria,@RequestBody CategoriaRepresentation.CriarOuAtualizar atualizar){
        Categoria categoriaAtualizada = this.categoriaService.atualizar(idCategoria,atualizar);
        CategoriaRepresentation.Detalhes detalhes = CategoriaRepresentation.Detalhes.from(categoriaAtualizada);

        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/idCategoria")
    public ResponseEntity<CategoriaRepresentation.Detalhes> buscarCategpria(@PathVariable Long idCategoria){
        Categoria categoria = this.categoriaService.buscarUmaCategoria(idCategoria);

        CategoriaRepresentation.Detalhes detalhes = CategoriaRepresentation.Detalhes.from(categoria);
        return ResponseEntity.ok(detalhes);
    }
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Categoria> deleteCategoria(@PathVariable Long idCategoria){
        categoriaService.deleteCategoria(idCategoria);
        return ResponseEntity.noContent().build();
    }
}

