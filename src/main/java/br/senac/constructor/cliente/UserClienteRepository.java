package br.senac.constructor.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClienteRepository extends JpaRepository<UserCliente, Long> {
   

}

