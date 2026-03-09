package com.challenge.forumhub.controller;

import com.challenge.forumhub.domain.topicos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@Controller
public class TopicoController {

    @Autowired
    private TopicoService topicoService;
    @Autowired
    private TopicoRepository topicoRepository;


  @PostMapping
   @Transactional
   public ResponseEntity cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dadosCadastroTopico){
      var topicoCadastrado = topicoService.construir(dadosCadastroTopico);

        topicoRepository.save(topicoCadastrado);
              return ResponseEntity.ok(topicoCadastrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity visualizarDetalhesTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(
            @PageableDefault(size = 10) Pageable paginacao) {

        var lista = topicoRepository
                .findAllByEstadoNot(Estado.DESATIVADO, paginacao)
                .map(DadosListagemTopico::new);
        return ResponseEntity.ok(lista);
    }


    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity desativarTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        topico.desativarTopico();
      return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity atualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoTopico dados){

        topicoService.atualizar(id, dados);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/concluir")
    @Transactional
    public ResponseEntity concluirTopico(@PathVariable Long id){

        var topico = topicoRepository.getReferenceById(id);
        topico.concluirTopico();

        return ResponseEntity.noContent().build();
    }
}
