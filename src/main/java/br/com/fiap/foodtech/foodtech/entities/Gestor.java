package br.com.fiap.foodtech.foodtech.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Gestor extends Usuario {

    public Gestor(String nome, String email, String login, String senha) {
        super(nome, email, login, senha);
        setDataUltimaAlteracao(LocalDateTime.now());
    }

}
