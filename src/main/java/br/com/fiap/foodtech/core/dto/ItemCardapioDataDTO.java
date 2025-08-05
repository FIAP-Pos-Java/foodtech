package br.com.fiap.foodtech.core.dto;

import java.math.BigDecimal;

public record ItemCardapioDataDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponibilidadeRestaurante,
        String caminhoFoto
) {}