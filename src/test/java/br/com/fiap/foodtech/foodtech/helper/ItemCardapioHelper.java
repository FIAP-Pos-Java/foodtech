package br.com.fiap.foodtech.foodtech.helper;

import br.com.fiap.foodtech.foodtech.dto.ItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.entities.ItemCardapio;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class ItemCardapioHelper {

    public static ItemCardapio gerarItemCardapio() {
        return ItemCardapio.builder()
                .descricao("Deliciosa pizza de queijo")
                .preco(BigDecimal.valueOf(12.99))
                .caminhoFoto("caminho/para/foto.jpg")
                .build();
    }

    public static ItemCardapioDTO gerarItemCardapioDto() {
        return ItemCardapioDTO.builder()
                .descricao("Deliciosa pizza de calabresa")
                .preco(BigDecimal.valueOf(15.99))
                .caminhoFoto("caminho/para/foto_calabresa.jpg")
                .build();
    }

    public static ItemCardapio gerarItemCardapioCompleto() {
        var timestamp = LocalDateTime.now();
        return ItemCardapio.builder()
                .id(1L) // Simulando ID gerado)
                .nome("Pizza Queijo")
                .descricao("Deliciosa Pizza de queijo com borda de catupiry, oregano e tomate")
                .preco(BigDecimal.valueOf(79))
                .disponibilidadeRestaurante(true)
                .caminhoFoto("caminho/para/foto.jpg")
                .build();
    }
    public static ItemCardapioDTO gerarItemCardapioCompletoDto() {
        var timestamp = LocalDateTime.now();
        return ItemCardapioDTO.builder()
                .nome("Pizza Calabresa")
                .descricao("Deliciosa pizza de calabresa com borda de catupiry, cebola e azeitona")
                .preco(BigDecimal.valueOf(69))
                .disponibilidadeRestaurante(true)
                .caminhoFoto("caminho/para/foto.jpg")
                .build();
    }
}
