package br.senac.constructor.cliente;


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

    public List<UserCliente> getAllUsers() {
        return userRepository.findAll();
    }

    public UserCliente getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserCliente with id " + id + " not found"));
    }

    public UserCliente createUser(UserCliente user) {
        return userRepository.save(user);
    }

    public UserCliente updateUser(Long id, UserCliente user) {
        UserCliente existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserCliente with id " + id + " not found"));
        existingUser.setCpf(user.getCpf());
        existingUser.setContato(user.getContato());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}