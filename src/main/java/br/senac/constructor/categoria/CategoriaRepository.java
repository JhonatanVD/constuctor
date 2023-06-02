package br.senac.constructor.categoria;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long>,
        QuerydslPredicateExecutor<Categoria> {
}
