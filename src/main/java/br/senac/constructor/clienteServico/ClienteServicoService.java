package br.senac.constructor.clienteServico;

import br.senac.constructor.cliente.Cliente;
import br.senac.constructor.cliente.ClienteService;
import br.senac.constructor.exception.NotFoundException;
import br.senac.constructor.servico.Servico;
import br.senac.constructor.servico.ServicoService;
import br.senac.constructor.utils.StatusEnum;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ClienteServicoService{

    private ClienteServicoRepository clienteServicoRepository;
    private ServicoService servicoService;
    private ClienteService clienteService;

    @Transactional
    //Todas operações são feitas na mesma sessão do BD
    public ClienteServico criarClienteServico(ClienteServicoRepresentation.CriarOuAtualizar criar){
        Servico servico = this.servicoService.buscarUmServico(criar.getServico());
        Cliente cliente = this.clienteService.buscarUmCliente(criar.getCliente());

        return this.clienteServicoRepository.save(ClienteServico.builder()
                        .valor(criar.getValor())
                        .status(StatusEnum.ATIVO)
                        .data(criar.getData())
                        .servico(servico)
                        .cliente(cliente)
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
                .status(atualizar.getStatus())
                .data(atualizar.getData())
                .build();
        return this.clienteServicoRepository.save(clienteServicoParaAtualizar);
    }
    public ClienteServico buscarumclienteservico(Long idClienteServico) {
        Optional<ClienteServico> clienteServicoAtual = this.clienteServicoRepository.findById(idClienteServico);
        if (clienteServicoAtual.isPresent()) {
            return clienteServicoAtual.get();
        } else {
            throw new NotFoundException("CLiente-serviço não encontrado");
        }
    }
    public void excluir(Long idServico) {
        this.buscarumclienteservico(idServico);
        clienteServicoRepository.deleteById(idServico);
    }

}

