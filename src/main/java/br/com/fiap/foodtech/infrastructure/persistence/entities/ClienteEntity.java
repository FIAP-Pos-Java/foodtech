package br.com.fiap.foodtech.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

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

    // Relacionamento com Login - cascade para salvar junto
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_login", referencedColumnName = "id")
    private LoginEntity login;

    // Relacionamento com Endereco - cascade para salvar junto
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private EnderecoEntity endereco;

    @PrePersist
    @PreUpdate
    protected void onCreateOrUpdate() {
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}