package br.com.fiap.foodtech.foodtech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "gestor")
public class Gestor extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Gestor(String nome, String email, Login login, Endereco endereco) {
        super(nome, email, login, endereco);
        setDataUltimaAlteracao(LocalDateTime.now());
    }

}
