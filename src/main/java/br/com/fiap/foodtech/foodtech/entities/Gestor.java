package br.com.fiap.foodtech.foodtech.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gestor extends Usuario {

    private Long id;

    public Gestor(String nome, String email, String login, String senha, LocalDateTime dataUltimaAlteracao, Endereco endereco, Long id) {
        super(nome, email, login, senha, dataUltimaAlteracao, endereco);
        this.id = id;
    }
}
