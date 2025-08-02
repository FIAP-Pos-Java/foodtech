package br.com.fiap.foodtech.foodtech.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "gestor")
    private List<Restaurante> restaurantes;

    public Gestor(String nome, String email, String tipoUsuario, Login login, Endereco endereco) {
        super(nome, email, tipoUsuario, login, endereco);
        setDataUltimaAlteracao(LocalDateTime.now());
    }
}
