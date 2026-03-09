package com.challenge.forumhub.controller;

import com.challenge.forumhub.domain.autores.AutorRepository;
import com.challenge.forumhub.domain.autores.AutorService;
import com.challenge.forumhub.domain.autores.DadosCadastroAutor;
import com.challenge.forumhub.domain.cursos.Curso;
import com.challenge.forumhub.domain.cursos.CursoRepository;
import com.challenge.forumhub.domain.cursos.CursoService;
import com.challenge.forumhub.domain.cursos.DadosCadastroCurso;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name= "bearer-key")
public class CursoController {
    @Autowired
    private CursoService service;

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroCurso dados){

        var curso = new Curso(dados);
        var cursoSalvo = repository.save(curso);

        return ResponseEntity.ok(cursoSalvo);
    }
}
