package com.challenge.forumhub.domain.topicos;

import com.challenge.forumhub.domain.autores.Autor;
import com.challenge.forumhub.domain.cursos.Curso;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name ="Topico")
@Table(name="topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public void atualizar(@Valid DadosAtualizacaoTopico dados, Curso curso){
        this.curso = curso;
        this.mensagem = dados.mensagem();
        this.titulo = dados.titulo();
    }

    public void desativarTopico() {
        this.estado = Estado.DESATIVADO;
    }
    public void concluirTopico(){
        this.estado = Estado.CONCLUIDO;
    }
}
