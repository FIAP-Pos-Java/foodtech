package br.com.fiap.foodtech.foodtech.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email", unique = true)
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_login", referencedColumnName = "id")
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    public Usuario(String nome, String email, Login login, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.endereco = endereco;
    }

}
