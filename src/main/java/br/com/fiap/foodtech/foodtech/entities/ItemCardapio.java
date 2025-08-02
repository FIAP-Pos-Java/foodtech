package br.com.fiap.foodtech.foodtech.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "item_cardapio")
@Builder(toBuilder = true)
public class ItemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco", precision = 9, scale = 2)
    private BigDecimal preco;

    @Column(name = "disponibilidade_restaurante", nullable = false)
    private Boolean disponibilidadeRestaurante;

    @Column(name = "caminho_foto")
    private String caminhoFoto;

    public ItemCardapio(Long id) {
        this.id = id;
    }

    public ItemCardapio(String nome, String descricao, BigDecimal preco, Boolean disponibilidadeRestaurante, String caminhoFoto) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponibilidadeRestaurante = disponibilidadeRestaurante;
        this.caminhoFoto = caminhoFoto;
    }
}

