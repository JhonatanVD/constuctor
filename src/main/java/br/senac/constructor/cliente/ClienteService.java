package br.senac.constructor.cliente;


import br.senac.constructor.exception.BusinessException;
import br.senac.constructor.exception.NotFoundException;
import br.senac.constructor.usuario.Usuario;
import br.senac.constructor.usuario.UsuarioService;
import br.senac.constructor.utils.StatusEnum;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Builder
@AllArgsConstructor
@Slf4j
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;
    public Cliente criarCliente(ClienteRepresentation.CriarOuAtualizar criarOuAtualizar) {
        Usuario usuario = this.usuarioService.buscarUmUsuario(criarOuAtualizar.getUsuario()); //verifica se existe um usuario

        Cliente cliente = Cliente.builder()
                .contato(criarOuAtualizar.getContato())
                .documento(criarOuAtualizar.getDocumento())
                .usuario(usuario)
                .status(StatusEnum.ATIVO)
                .build();

        return clienteRepository.save(cliente);
    }
    public Page<Cliente> buscarTodos(Pageable pageable) {
        return  this.clienteRepository.findAll(pageable);
    }
    public Page<Cliente> buscarTodos(Predicate filtroUri, Pageable pageable) {
        return this.clienteRepository.findAll(pageable);
    }

    public Cliente atualizar(Long idCliente, ClienteRepresentation.CriarOuAtualizar atualizar) {
        Cliente clienteParaAtualizar  = Cliente.builder()
                .contato(atualizar.getContato())
                .documento(atualizar.getDocumento())
                .status(atualizar.getStatus())
                .build();

        return this.clienteRepository.save(clienteParaAtualizar);
    }

    public void deleteUser(Long id) {clienteRepository.deleteById(id);
    }


    public Cliente buscarUmCliente(Long idCLiente) {
        Optional<Cliente> clienteAtual = this.clienteRepository.findById(idCLiente);
        if (clienteAtual.isPresent()) {
            return clienteAtual.get();
        } else {
            throw new NotFoundException("CLiente não encontrado");
        }
    }

    public void excluir(Long id) {
        Cliente cliente = this.buscarUmCliente(id);
        if(cliente.getStatus().equals(StatusEnum.ATIVO)){
            cliente.setStatus(StatusEnum.INATIVO);
            this.clienteRepository.save(cliente);
        }else{
            throw new BusinessException("Cliente já está INATIVO");
        }

    }
}
