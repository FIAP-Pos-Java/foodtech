package br.com.fiap.foodtech.foodtech.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataUltimaAlteracao;

    @OneToOne
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    public Usuario() {
    }

    public Usuario(String nome, String email, String login, String senha) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

}
