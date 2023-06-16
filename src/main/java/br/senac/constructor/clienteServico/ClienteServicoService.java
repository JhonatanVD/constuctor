package br.senac.constructor.clienteServico;

import br.senac.constructor.cliente.ClienteService;
import br.senac.constructor.exception.NotFoundException;
import br.senac.constructor.servico.Servico;
import br.senac.constructor.servico.ServicoService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ClienteServicoService{

    private ClienteServicoRepository clienteServicoRepository;
    private ClienteService clienteService;
    private ServicoService servicoService;


    public ClienteServico criarClienteServico(ClienteServicoRepresentation.CriarOuAtualizar criar){
        Servico servico = this.servicoService.buscarUmServico(criar.getServico());
        //ClienteService clienteService = this.clienteService.getUserById();
        return this.clienteServicoRepository.save(ClienteServico.builder()
                        .valor(criar.getValor())
                        .data((Date) criar.getData())
                .build());
    }

    public Page<ClienteServico> buscarTodos(Pageable pageable){
        return this.clienteServicoRepository.findAll(pageable);
    }
    public Page<ClienteServico> buscarTodos(Predicate filtroURI,Pageable pageable){
        return this.clienteServicoRepository.findAll(pageable);
    }

    public ClienteServico atualizar(Long idClienteServico, ClienteServicoRepresentation.CriarOuAtualizar atualizar){
        ClienteServico clienteServicoParaAtualizar = ClienteServico.builder()
                .valor(atualizar.getValor())
                .data((Date) atualizar.getData())
                .build();
        return this.clienteServicoRepository.save(clienteServicoParaAtualizar);
    }

    public ClienteServico buscarUmCLienteServico(Long idClienteServico) {
        Optional<ClienteServico> clienteServicoAtual = this.clienteServicoRepository.findById(idClienteServico);
        if (clienteServicoAtual.isPresent()) {
            return clienteServicoAtual.get();
        } else {
            throw new NotFoundException("CLiente-serviço não encontrado");
        }
    }
    public void excluir(Long id) {
        clienteServicoRepository.deleteById(id);
    }
}

