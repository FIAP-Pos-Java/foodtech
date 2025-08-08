package br.com.fiap.foodtech.foodtech.infrastructure.data.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cliente")
public class ClienteEntity extends UsuarioEntity {

    public ClienteEntity(String nome, String email, String tipoUsuario, LoginEntity login, EnderecoEntity endereco) {
        super(nome, email, tipoUsuario, login, endereco);
        setDataUltimaAlteracao(LocalDateTime.now());
    }

}
