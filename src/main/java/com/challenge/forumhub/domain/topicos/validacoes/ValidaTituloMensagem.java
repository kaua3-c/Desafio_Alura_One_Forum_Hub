package com.challenge.forumhub.domain.topicos.validacoes;

import com.challenge.forumhub.domain.topicos.DadosCadastroTopico;
import com.challenge.forumhub.domain.topicos.TopicoRepository;
import com.challenge.forumhub.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaTituloMensagem implements ValidadorCadastroTopico {
    @Autowired
    private TopicoRepository repository;

    @Override
    public void validar(DadosCadastroTopico dados) {
        if(repository.existsByTitulo(dados.titulo())){
            throw new ValidacaoException("Já existe um tópico com este título!");
        }
        if(repository.existsByMensagem(dados.mensagem())){
            throw new ValidacaoException("Já existe um tópico com esta mensagem!");
        }

    }

}
