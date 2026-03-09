package com.challenge.forumhub.domain.topicos;

import java.time.LocalDateTime;

    public record DadosListagemTopico (String titulo,
                                       String mensagem,
                                       LocalDateTime dataCriacao,
                                       Estado estado,
                                       Long Autor,
                                       Long Curso) {

public DadosListagemTopico(Topico topico){
    this(topico.getTitulo(),
            topico.getMensagem(),
            topico.getDataCriacao(),
            topico.getEstado(),
            topico.getAutor().getId(),
            topico.getCurso().getId());
}
}