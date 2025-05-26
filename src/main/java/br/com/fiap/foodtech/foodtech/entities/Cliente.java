package br.com.fiap.foodtech.foodtech.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {

    public Cliente(String nome, String email, String login, String senha) {
        super(nome, email, login, senha);
        setDataUltimaAlteracao(LocalDateTime.now());
    }

}
