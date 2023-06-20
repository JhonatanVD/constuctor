package br.senac.constructor.cliente;

import br.senac.constructor.servico.Servico;
import br.senac.constructor.usuario.Usuario;
import br.senac.constructor.usuario.UsuarioRepresentation;
import br.senac.constructor.utils.Paginacao;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/cliente")
@CrossOrigin("*")
@AllArgsConstructor
public class ClienteController {

    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteRepresentation.Detalhes> criarCliente
            (@RequestBody @Valid ClienteRepresentation.CriarOuAtualizar criar){
        Cliente cliente = this.clienteService.criarCliente(criar);

        ClienteRepresentation.Detalhes detalhes = ClienteRepresentation.Detalhes.from(cliente);
        return ResponseEntity.ok(detalhes);


    }
    @GetMapping("/all")
    public ResponseEntity<Paginacao> buscarCLiente(
            @QuerydslPredicate(root = Cliente.class) Predicate filtroURI,
            @RequestParam(name = "tamanhoPagina", defaultValue = "30") int tamanhoPagina,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") int paginaSelecionada){
        Pageable pageable = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Cliente> clienteList = Objects.isNull(filtroURI)?
                this.clienteService.buscarTodos(pageable):
                this.clienteService.buscarTodos(filtroURI, pageable);

        Paginacao paginacao = Paginacao.builder()
                .paginaSelecionada(paginaSelecionada)
                .tamanhoPagina(tamanhoPagina)
                .totalRegistros(clienteList.getTotalElements())
                .proximaPagina(clienteList.hasNext())
                .conteudo(ClienteRepresentation.Lista
                        .from(clienteList.getContent()))
                .build();

        return ResponseEntity.ok(paginacao);
    }
    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteRepresentation.Detalhes> atualizarCliente(@PathVariable Long idCliente, @RequestBody @Valid ClienteRepresentation.CriarOuAtualizar atualizar){
        Cliente ClienteAtualizado = this.clienteService.atualizar(idCliente, atualizar);
        ClienteRepresentation.Detalhes detalhes = ClienteRepresentation.Detalhes.from(ClienteAtualizado);

        return ResponseEntity.ok(detalhes);
    }
    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteRepresentation.Detalhes> buscarUmCliente(
            @PathVariable Long idCliente){
        Cliente cliente = this.clienteService.buscarUmCliente(idCliente);

        ClienteRepresentation.Detalhes detalhes = ClienteRepresentation.Detalhes.from(cliente);
        return ResponseEntity.ok(detalhes);
    }
    @DeleteMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCLiente(@PathVariable("idCliente") Long idCliente){
        this.clienteService.excluir(idCliente);
    }
}


