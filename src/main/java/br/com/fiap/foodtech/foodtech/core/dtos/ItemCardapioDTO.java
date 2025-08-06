package br.com.fiap.foodtech.foodtech.core.dtos;

import java.math.BigDecimal;

public record ItemCardapioDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponibilidadeRestaurante,
        String caminhoFoto
) { }
