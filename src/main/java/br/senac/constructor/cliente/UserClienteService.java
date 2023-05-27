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
    @Autowired
    private UserClienteRepository userRepository;
    private final UsuarioService usuarioService;

    public List<UserCliente> getAllUsers() {
        return userRepository.findAll();
    }

    public UserCliente getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserCliente with id " + id + " not found"));
    }

    public UserCliente createUser(ClienteRepresentation.CriarOuAtualizar criarOuAtualizar) {
        Usuario usuario = this.usuarioService.buscarUmUsuario(criarOuAtualizar.getUsuario());

        UserCliente userCliente = UserCliente.builder()
                .contato(criarOuAtualizar.getContato())
                .documento(criarOuAtualizar.getDocumento())
                .usuario(usuario)
                .build();

        return userRepository.save(userCliente);
    }

    public UserCliente updateUser(Long id, UserCliente user) {
        UserCliente existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserCliente with id " + id + " not found"));
        existingUser.setDocumento(user.getDocumento());
        existingUser.setContato(user.getContato());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}