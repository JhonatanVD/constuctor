package br.senac.constructor.prestador;

import br.senac.constructor.utils.Paginacao;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/prestador")
@CrossOrigin("*")
@AllArgsConstructor
public class PrestadorController {

    private PrestadorService prestadorService;
    @PostMapping
    public ResponseEntity<PrestadorRepresentation.Detalhes> criarPrestador(
            @RequestBody @Valid PrestadorRepresentation.CriarOuAtualizar criar){

        Prestador prestador = this.prestadorService.criarPrestador(criar);

        PrestadorRepresentation.Detalhes detalhes = PrestadorRepresentation.Detalhes.from(prestador);
        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/all")
    public ResponseEntity<Paginacao> buscarPrestador(
            @QuerydslPredicate(root = Prestador.class) Predicate filtroURI,
            @RequestParam(name = "tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Prestador> prestadorList = Objects.isNull(filtroURI)?
                this.prestadorService.buscarTodos(pageable):
                this.prestadorService.buscarTodos(filtroURI, pageable);

        Paginacao paginacao = Paginacao.builder()
                .paginaSelecionada(paginaSelecionada)
                .tamanhoPagina(tamanhoPagina)
                .totalRegistros(prestadorList.getTotalElements())
                .proximaPagina(prestadorList.hasNext())
                .conteudo(PrestadorRepresentation.Lista
                        .from(prestadorList.getContent()))
                .build();

        return ResponseEntity.ok(paginacao);
    }
    @GetMapping("/{idPrestador}")
    public ResponseEntity<PrestadorRepresentation.Detalhes> buscarUmPrestador(
            @PathVariable Long idPrestador){
        Prestador prestador = this.prestadorService.buscarUmPrestador(idPrestador);

        PrestadorRepresentation.Detalhes detalhes = PrestadorRepresentation.Detalhes.from(prestador);
        return ResponseEntity.ok(detalhes);
    }

    @PutMapping("/{idPrestador}")
    public ResponseEntity<PrestadorRepresentation.Detalhes> atualizarPrestador(@PathVariable Long idPrestador, @RequestBody PrestadorRepresentation.CriarOuAtualizar atualizar){
        Prestador prestadorAtualizado = this.prestadorService.atualizar(idPrestador, atualizar);
        PrestadorRepresentation.Detalhes detalhes = PrestadorRepresentation.Detalhes.from(prestadorAtualizado);

        return ResponseEntity.ok(detalhes);
    }
    @DeleteMapping("/{idPrestador}")
    public ResponseEntity excluirPrestador(@PathVariable("idPrestador") Long id){
        this.prestadorService.excluir(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

