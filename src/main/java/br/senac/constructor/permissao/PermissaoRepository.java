package br.senac.constructor.permissao;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;


public interface PermissaoRepository extends PagingAndSortingRepository<Permissao , Long>,
        QuerydslPredicateExecutor<Permissao> {
}
