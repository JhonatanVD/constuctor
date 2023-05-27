package br.senac.constructor.prestadorServico;

import br.senac.constructor.permissao.Permissao;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrestadorRepository extends PagingAndSortingRepository<Permissao, Long>,
        QuerydslPredicateExecutor<Permissao> {
}
