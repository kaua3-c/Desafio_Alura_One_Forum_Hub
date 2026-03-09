package com.challenge.forumhub.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findAllByEstadoNot(Estado estado, Pageable pageable);

    boolean existsByMensagem(@NotBlank String mensagem);

    boolean existsByTitulo(@NotBlank String titulo);

//@Query(
//"SELECT t.estado from Topico t where t.id =: id"
//)
//Boolean findAtivoById(long id);
}
