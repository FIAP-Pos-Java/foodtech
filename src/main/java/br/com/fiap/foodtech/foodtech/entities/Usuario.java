package br.com.fiap.foodtech.foodtech.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class Usuario {
    protected String nome;
    protected String email;
    protected String login;
    protected String senha;
    protected LocalDate dataAtualizacao;
    protected Endereco endereco;

    public Usuario(String nome, String email, String senha, String login, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.login = login;
        this.endereco = endereco;
    }
}

