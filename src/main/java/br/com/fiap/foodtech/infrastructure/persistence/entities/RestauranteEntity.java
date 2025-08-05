package br.com.fiap.foodtech.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "restaurante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo_cozinha", nullable = false)
    private String tipoCozinha;

    @Column(name = "horario_abertura", nullable = false)
    private LocalTime horarioAbertura;

    @Column(name = "horario_fechamento", nullable = false)
    private LocalTime horarioFechamento;

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gestor", referencedColumnName = "id")
    private GestorEntity gestor;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private EnderecoEntity endereco;

    @PrePersist
    @PreUpdate
    protected void onCreateOrUpdate() {
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}