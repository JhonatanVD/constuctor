package br.senac.constructor.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>,
        QuerydslPredicateExecutor<Usuario> {
    @Query("UPDATE Usuario u SET u.excluido = true WHERE u.id = :id")
    void excluir(@Param("id") Long id);
}
