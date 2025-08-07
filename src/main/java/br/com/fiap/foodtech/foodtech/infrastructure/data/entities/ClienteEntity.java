package br.com.fiap.foodtech.foodtech.infrastructure.data.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
public class ClienteEntity extends UsuarioEntity {

    public ClienteEntity(String nome, String email, String tipoUsuario, LoginEntity login, EnderecoEntity endereco) {
        super(nome, email, tipoUsuario, login, endereco);
    }

    public ClienteEntity(Long id, String nome, String email, String tipoUsuario, LoginEntity login, EnderecoEntity endereco) {
        super(id, nome, email, tipoUsuario, login, endereco);
    }
}
