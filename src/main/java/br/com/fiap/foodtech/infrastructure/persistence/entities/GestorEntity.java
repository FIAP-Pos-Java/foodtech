package br.com.fiap.foodtech.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "gestor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GestorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "tipo_usuario")
    private String tipoUsuario;

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_login", referencedColumnName = "id")
    private LoginEntity login;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private EnderecoEntity endereco;

    @PrePersist
    @PreUpdate
    protected void onCreateOrUpdate() {
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}