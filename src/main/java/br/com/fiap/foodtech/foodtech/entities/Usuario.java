package br.com.fiap.foodtech.foodtech.entities;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Usuario {
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAlteracao;
    private Endereco endereco;
}

