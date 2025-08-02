package br.com.fiap.foodtech.foodtech.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "restaurante")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo_cozinha")
    private String tipoCozinha;

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "horario_abertura")
    private LocalTime horarioAbertura;

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "horario_fechamento")
    private LocalTime horarioFechamento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @ManyToOne
    @JoinColumn(name = "id_gestor", referencedColumnName = "id")
    private Gestor gestor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;

    public Restaurante(
            String nome,
            String tipoCozinha,
            LocalTime horarioAbertura,
            LocalTime horarioFechamento,
            Gestor gestor, Endereco endereco) {
        this.nome = nome;
        this.tipoCozinha = tipoCozinha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.gestor = gestor;
        this.endereco = endereco;
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}
