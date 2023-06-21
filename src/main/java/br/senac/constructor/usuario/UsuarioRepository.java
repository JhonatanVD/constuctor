package br.senac.constructor.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>,
        QuerydslPredicateExecutor<Usuario> {
    boolean existsUserByEmail(String email);

    Optional<Usuario> findUserByEmail(String email);
}
