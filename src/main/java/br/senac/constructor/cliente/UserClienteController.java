package br.senac.constructor.cliente;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cliente")
@CrossOrigin("*")
@AllArgsConstructor
public class UserClienteController {

    private UserClienteService userService;


    @GetMapping("/all")
    @ApiOperation(value = "Get all users", response = List.class)
    public List<Cliente> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get one", response = List.class)
    public ResponseEntity<Cliente> getUserById(@PathVariable Long id) {
        Cliente user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @ApiOperation(value = "create", response = Cliente.class)
    public ResponseEntity<Cliente> createUser(@RequestBody ClienteRepresentation.CriarOuAtualizar cliente) {
        Cliente createdUser = userService.createUser(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateUser(@PathVariable Long id, @RequestBody ClienteRepresentation.CriarOuAtualizar atualizar) {
        Cliente updatedUser = userService.updateUser(id, atualizar);
        return ResponseEntity.ok(updatedUser);
        //verificar metodo
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
