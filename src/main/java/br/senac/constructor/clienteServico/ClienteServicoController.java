package br.senac.constructor.clienteServico;

import br.senac.constructor.servico.Servico;
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
@RequestMapping("/api/clienteServico")
@AllArgsConstructor
@CrossOrigin("*")
public class ClienteServicoController {

    private ClienteServicoService clienteServicoService;
    @PostMapping
    public ResponseEntity<ClienteServicoRepresentation.Detalhes> criarCLienteServico(
            @PathVariable @Valid ClienteServicoRepresentation.CriarOuAtualizar criar){

        ClienteServico clienteServico = this.clienteServicoService.criarClienteServico(criar);

        ClienteServicoRepresentation.Detalhes detalhes = ClienteServicoRepresentation.Detalhes.from(clienteServico);
        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/all")
    public ResponseEntity<Paginacao> buscarUmClienteServico(
            @QuerydslPredicate(root = ClienteServico.class) Predicate filtroURI,
            @RequestParam(name = "tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<ClienteServico> clienteServicoList = Objects.isNull(filtroURI)?
                this.clienteServicoService.buscarTodos(pageable):
                this.clienteServicoService.buscarTodos(filtroURI, pageable);

        Paginacao paginacao = Paginacao.builder()
                .paginaSelecionada(paginaSelecionada)
                .tamanhoPagina(tamanhoPagina)
                .totalRegistros(clienteServicoList.getTotalElements())
                .proximaPagina(clienteServicoList.hasNext())
                .conteudo(ClienteServicoRepresentation.Lista
                        .from(clienteServicoList.getContent()))
                .build();

        return ResponseEntity.ok(paginacao);
    }
    @PutMapping("/{idClienteServico}")
    public ResponseEntity<ClienteServicoRepresentation.Detalhes> atualizarCLienteServico(@PathVariable Long idClienteServico, @RequestBody ClienteServicoRepresentation.CriarOuAtualizar atualizar){
        ClienteServico clienteServicoAtualizado = this.clienteServicoService.atualizar(idClienteServico, atualizar);
        ClienteServicoRepresentation.Detalhes detalhes = ClienteServicoRepresentation.Detalhes.from(clienteServicoAtualizado);

        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/{idClienteServico}")
    public ResponseEntity<ClienteServicoRepresentation.Detalhes> buscarUmClienteServico(
            @PathVariable Long idClienteServico){
        ClienteServico clienteServico = this.clienteServicoService.buscarUmCLienteServico(idClienteServico);

        ClienteServicoRepresentation.Detalhes detalhes = ClienteServicoRepresentation.Detalhes.from(clienteServico);
        return ResponseEntity.ok(detalhes);
    }
    @DeleteMapping("/{idCLienteServico}")
    public ResponseEntity<Servico> excluirCLienteServico(@PathVariable Long idCLienteServico) {
        clienteServicoService.excluir(idCLienteServico);
        return ResponseEntity.noContent().build();
    }
}
