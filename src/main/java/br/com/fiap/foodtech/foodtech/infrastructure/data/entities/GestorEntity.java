package br.com.fiap.foodtech.foodtech.infrastructure.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "gestor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GestorEntity extends UsuarioEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "gestor")
    private List<RestauranteEntity> restaurantes;

    public GestorEntity(String nome, String email, String tipoUsuario, LoginEntity login, EnderecoEntity endereco) {
        super(nome, email, tipoUsuario, login, endereco);
    }

    public GestorEntity(Long id, String nome, String email, String tipoUsuario, LoginEntity login, EnderecoEntity endereco) {
        super(id, nome, email, tipoUsuario, login, endereco);
    }
}
