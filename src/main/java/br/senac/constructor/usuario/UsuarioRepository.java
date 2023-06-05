package br.senac.constructor.usuario;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>,
        QuerydslPredicateExecutor<Usuario> {
    Optional<Usuario> findUserByEmail(String email);
}
