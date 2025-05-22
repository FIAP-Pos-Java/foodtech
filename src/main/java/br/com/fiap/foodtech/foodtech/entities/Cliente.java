package br.com.fiap.foodtech.foodtech.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Cliente extends Usuario {

    public Cliente(){ }

    public Cliente(String nome, String email, String login, String senha) {
        super(nome, email, login, senha);
    }


}
