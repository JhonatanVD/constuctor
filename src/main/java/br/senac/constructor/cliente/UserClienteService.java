package br.senac.constructor.cliente;


import br.senac.constructor.usuario.Usuario;
import br.senac.constructor.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Builder
@AllArgsConstructor
@Slf4j
@Service
public class UserClienteService {

    private ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;


    public List<Cliente> getAllUsers() {
        return clienteRepository.findAll();
    }

    public Cliente getUserById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserCliente with id " + id + " not found"));
    }

    public Cliente createUser(ClienteRepresentation.CriarOuAtualizar criarOuAtualizar) {
        Usuario usuario = this.usuarioService.buscarUmUsuario(criarOuAtualizar.getUsuario());

        Cliente cliente = Cliente.builder()
                .contato(criarOuAtualizar.getContato())
                .documento(criarOuAtualizar.getDocumento())
                .usuario(usuario)
                .build();

        return clienteRepository.save(cliente);
    }

    public Cliente updateUser(Long id, ClienteRepresentation.CriarOuAtualizar atualizar) {
        Cliente clienteParaAtualizar = Cliente.builder()
                .contato(atualizar.getContato())
                .documento(atualizar.getDocumento())
                .build();

        return this.clienteRepository.save(clienteParaAtualizar);

    }

    public void deleteUser(Long id) {clienteRepository.deleteById(id);
    }
}
