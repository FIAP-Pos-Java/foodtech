package br.com.fiap.foodtech.foodtech.infrastructure.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
public abstract class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "tipo_usuario")
    private String tipoUsuario;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_login", referencedColumnName = "id")
    private LoginEntity login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private EnderecoEntity endereco;

    public UsuarioEntity(String nome, String email, String tipoUsuario, LoginEntity login, EnderecoEntity endereco) {
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.login = login;
        this.endereco = endereco;
    }

    public UsuarioEntity(Long id, String nome, String email, String tipoUsuario, LoginEntity login, EnderecoEntity endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.login = login;
        this.endereco = endereco;
    }

    @PrePersist
    @PreUpdate
    protected void onCreateOrUpdate() {
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

}
