package br.com.fiap.foodtech.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_cardapio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "preco", precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(name = "disponibilidade_restaurante", nullable = false)
    private Boolean disponibilidadeRestaurante;

    @Column(name = "caminho_foto", nullable = false)
    private String caminhoFoto;

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @PrePersist
    @PreUpdate
    protected void onCreateOrUpdate() {
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}