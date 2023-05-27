package br.senac.constructor.usuario;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>,
        QuerydslPredicateExecutor<Usuario> {
}
