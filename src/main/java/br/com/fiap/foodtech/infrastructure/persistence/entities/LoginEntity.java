package br.com.fiap.foodtech.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * LoginEntity - Representa a tabela 'login' no banco de dados.
 *
 * Esta é uma entidade JPA que mapeia para o banco, diferente da
 * entidade Login do domínio que contém regras de negócio.
 */
@Entity
@Table(name = "login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @PrePersist
    @PreUpdate
    protected void onCreateOrUpdate() {
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}
