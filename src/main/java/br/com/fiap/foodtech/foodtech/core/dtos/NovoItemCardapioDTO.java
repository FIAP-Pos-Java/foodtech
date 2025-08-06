package br.com.fiap.foodtech.foodtech.core.dtos;

import java.math.BigDecimal;

public record NovoItemCardapioDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponibilidadeRestaurante,
        String caminhoFoto
) { }
