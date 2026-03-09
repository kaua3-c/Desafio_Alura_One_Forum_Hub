package com.challenge.forumhub.domain.autores;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity(name = "Autor")
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;


    public Autor(DadosCadastroAutor dadosCadastroAutor) {
        this.nome = dadosCadastroAutor.nome();
        this.email = dadosCadastroAutor.email();
        this.senha = dadosCadastroAutor.senha();
    }
}
