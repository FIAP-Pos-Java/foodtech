package br.com.fiap.foodtech.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "cep", nullable = false)
    private String cep;
}