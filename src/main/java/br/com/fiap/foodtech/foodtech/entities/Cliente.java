package br.com.fiap.foodtech.foodtech.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Cliente extends Usuario {
    private Long id;

    public Cliente(String nome, String email, String senha, String login, Endereco endereco) {
        super(nome, email, senha, login, endereco);
        dataAtualizacao = LocalDate.now();
    }
}
