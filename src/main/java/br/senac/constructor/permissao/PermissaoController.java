package br.senac.constructor.permissao;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/permissao")
@CrossOrigin("*")
@AllArgsConstructor
public class PermissaoController{

    private PermissaoService permissaoService;

    @PostMapping("/")
    public ResponseEntity<PermissaoRepresentation.Detalhes> criarPermissao(
            @RequestBody @Valid PermissaoRepresentation.CriarOuAtualizar criar){


        Permissao permissao = this.permissaoService.criarPermissao(criar);

        PermissaoRepresentation.Detalhes detalhes = PermissaoRepresentation.Detalhes.from(permissao);
        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/")
    public ResponseEntity<List<PermissaoRepresentation.Lista>> buscarPermissao(
            @QuerydslPredicate(root = Permissao.class) Predicate filtroURI,
            @RequestParam(name = "tamanhoPagina ", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
       Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Permissao> permissaoList = Objects.isNull(filtroURI)?
                this.permissaoService.buscarTodos(pageable):
                this.permissaoService.buscarTodos(filtroURI, pageable);
        return null;
   }

   @PutMapping("/{idPermissao}")
    public ResponseEntity<PermissaoRepresentation.Detalhes> atualizarPermissao(@PathVariable Long idPermisao, @RequestBody PermissaoRepresentation.CriarOuAtualizar atualizar){
        Permissao permissaoAtualizado = this.permissaoService.atualizar(idPermisao, atualizar);
        PermissaoRepresentation.Detalhes detalhes = PermissaoRepresentation.Detalhes.from(permissaoAtualizado);

        return ResponseEntity.ok(detalhes);
   }
    @GetMapping("/{idPermissao}")
    public ResponseEntity<PermissaoRepresentation.Detalhes> buscarUmaPermissao(
            @PathVariable Long idPermissao){
        Permissao permissao = this.permissaoService.buscarUmaPermissao(idPermissao);

        PermissaoRepresentation.Detalhes detalhes = PermissaoRepresentation.Detalhes.from(permissao);
        return ResponseEntity.ok(detalhes);
    }

}
