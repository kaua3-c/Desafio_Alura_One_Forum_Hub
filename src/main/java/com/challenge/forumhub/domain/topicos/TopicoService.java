package com.challenge.forumhub.domain.topicos;

import com.challenge.forumhub.domain.autores.AutorRepository;
import com.challenge.forumhub.domain.cursos.CursoRepository;
import com.challenge.forumhub.domain.topicos.validacoes.ValidadadorAtualizacaoTopico;
import com.challenge.forumhub.domain.topicos.validacoes.ValidadorCadastroTopico;
import com.challenge.forumhub.infra.exception.ValidacaoException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private List<ValidadadorAtualizacaoTopico> validarAtualizacao;
    @Autowired
    private List<ValidadorCadastroTopico> validarCadastro;
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired TopicoRepository topicoRepository;



    public Topico construir(DadosCadastroTopico dados){
        if(!autorRepository.existsById((dados.idAutor()))){
            throw new ValidacaoException("Não existe autor com o id informado!");
        }

        if(!cursoRepository.existsById(dados.idCurso())){
            throw new ValidacaoException("Não existe curso com o id informado!");
        }
        validarCadastro.forEach(v -> v.validar(dados));

        return new Topico(null, dados.titulo(),
                dados.mensagem(),
                LocalDateTime.now(),
                Estado.ATIVADO,
                autorRepository.getReferenceById(dados.idAutor()),
                cursoRepository.getReferenceById(dados.idCurso()));

    }

    public Topico retornar(Long id){
        if(!topicoRepository.existsById(id)){
            throw new ValidacaoException("Não existe tópico com o id informado!");
        }

        return topicoRepository.findById(id).get();
    }
    @Transactional
    public void atualizar(Long id, @Valid DadosAtualizacaoTopico dados){
        if(topicoRepository.findById(id).isEmpty()){
            throw new ValidacaoException("Não existe tópico com este id!");
        }

        if(!cursoRepository.existsById(dados.idCurso())){
            throw new ValidacaoException("Não existe curso com este id!");
        }

        var curso = cursoRepository.getReferenceById(dados.idCurso());

        validarAtualizacao.forEach(v -> v.validar(dados));

        var topico = topicoRepository.getReferenceById(id);
        topico.atualizar(dados, curso);
    }

}
