package br.senac.constructor.clienteServico;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteServicoRepository extends PagingAndSortingRepository<ClienteServico,Long>,
        QuerydslPredicateExecutor<ClienteServico>{
}
