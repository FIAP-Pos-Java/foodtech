package br.com.fiap.foodtech.foodtech.infrastructure.data.entities;

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
public class GestorEntity extends UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "gestor")
    private List<RestauranteEntity> restaurantes;

    public GestorEntity(String nome, String email, String tipoUsuario, LoginEntity login, EnderecoEntity endereco) {
        super(nome, email, tipoUsuario, login, endereco);
        setDataUltimaAlteracao(LocalDateTime.now());
    }
}
