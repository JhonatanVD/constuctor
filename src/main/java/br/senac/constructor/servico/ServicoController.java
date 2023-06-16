package br.senac.constructor.servico;


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

@AllArgsConstructor
@RestController
@RequestMapping("/api/servico")
@CrossOrigin("*")
public class ServicoController {

    private ServicoService servicoService;
    private ServicoRepository servicoRepository;
    @PostMapping
    public ResponseEntity<ServicoRepresentation.Detalhes> criarServico(
            @RequestBody @Valid ServicoRepresentation.CriarOuAtualizar criar){

        Servico servico = this.servicoService.criarServico(criar);

        ServicoRepresentation.Detalhes detalhes = ServicoRepresentation.Detalhes.from(servico);
        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/all")
    public ResponseEntity<Paginacao> buscarServico(
            @QuerydslPredicate(root = Servico.class) Predicate filtroURI,
            @RequestParam(name = "tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Servico> servicoList = Objects.isNull(filtroURI)?
                this.servicoService.buscarTodos(pageable):
                this.servicoService.buscarTodos(filtroURI, pageable);

        Paginacao paginacao = Paginacao.builder()
                .paginaSelecionada(paginaSelecionada)
                .tamanhoPagina(tamanhoPagina)
                .totalRegistros(servicoList.getTotalElements())
                .proximaPagina(servicoList.hasNext())
                .conteudo(ServicoRepresentation.Lista
                        .from(servicoList.getContent()))
                .build();

        return ResponseEntity.ok(paginacao);
    }

    @PutMapping("{idServico}")
    public ResponseEntity<ServicoRepresentation.Detalhes> atualizarServico(
            @PathVariable Long idServico, @RequestBody ServicoRepresentation.CriarOuAtualizar atualizar){
        Servico servicoAtualizado = this.servicoService.atualizar(idServico, atualizar);
        ServicoRepresentation.Detalhes detalhes = ServicoRepresentation.Detalhes.from(servicoAtualizado);

        return ResponseEntity.ok(detalhes);
    }

    @GetMapping("{idServico}")
    public ResponseEntity<ServicoRepresentation.Detalhes> buscarUmUsuario(@PathVariable Long idServico){
        Servico servico = this.servicoService.buscarUmServico(idServico);

        ServicoRepresentation.Detalhes detalhes = ServicoRepresentation.Detalhes.from(servico);
        return ResponseEntity.ok(detalhes);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Servico> excluirServico(@PathVariable Long id) {
        servicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
