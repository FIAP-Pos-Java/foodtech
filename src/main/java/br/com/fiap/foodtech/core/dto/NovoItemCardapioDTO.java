package br.com.fiap.foodtech.core.dto;

import java.math.BigDecimal;

public record NovoItemCardapioDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponibilidadeRestaurante,
        String caminhoFoto
) {}