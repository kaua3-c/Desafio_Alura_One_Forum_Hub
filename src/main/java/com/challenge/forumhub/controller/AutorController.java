package com.challenge.forumhub.controller;

import com.challenge.forumhub.domain.autores.Autor;
import com.challenge.forumhub.domain.autores.AutorRepository;
import com.challenge.forumhub.domain.autores.AutorService;
import com.challenge.forumhub.domain.autores.DadosCadastroAutor;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class AutorController {
@Autowired
    private AutorService autorService;
@Autowired
    private AutorRepository autorRepository;

@PostMapping
@RequestMapping("/autores")
    @Transactional
    public ResponseEntity cadastrarAutor(@RequestBody  DadosCadastroAutor dadosCadastroAutor){
    var autorCadastrado = new Autor(dadosCadastroAutor);
    autorRepository.save(autorCadastrado);
    return ResponseEntity.ok(autorCadastrado);


}

}
