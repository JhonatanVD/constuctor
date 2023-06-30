package br.senac.constructor.servico;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ServicoRepository extends PagingAndSortingRepository<Servico, Long>,
        QuerydslPredicateExecutor<Servico> {

}
