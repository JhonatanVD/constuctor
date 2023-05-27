package br.senac.constructor.prestador;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrestadorRepository extends PagingAndSortingRepository<Prestador, Long>,
        QuerydslPredicateExecutor<Prestador> {
    boolean findByUsuario(Long id);
}
