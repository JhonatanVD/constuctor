package br.senac.constructor.Prestador;

import br.senac.constructor.permissao.Permissao;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrestadorRepository extends PagingAndSortingRepository<Prestador, Long>,
        QuerydslPredicateExecutor<Prestador> {
}
