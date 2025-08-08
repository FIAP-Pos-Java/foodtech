package br.com.fiap.foodtech.foodtech.core.domain.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;

class ItemCardapioTest {

    @Test
    void deveCriarItemCardapioComDadosValidos() {
        ItemCardapio item = new ItemCardapio(
                "Pizza Margherita",
                "Pizza com molho de tomate e mussarela",
                new BigDecimal("39.90"),
                true,
                "/imagens/pizza.jpg"
        );

        assertEquals("Pizza Margherita", item.getNome());
        assertEquals("Pizza com molho de tomate e mussarela", item.getDescricao());
        assertEquals(new BigDecimal("39.90"), item.getPreco());
        assertTrue(item.getDisponibilidadeRestaurante());
        assertEquals("/imagens/pizza.jpg", item.getCaminhoFoto());
    }

    @Test
    void deveLancarExcecaoParaNomeInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ItemCardapio(
                        "",
                        "Descrição válida",
                        new BigDecimal("10.00"),
                        true,
                        "/foto.jpg"
                )
        );
        assertEquals("Nome é obrigatório", exception.getMessage());
    }

    @Test
    void deveAtualizarPrecoComValorValido() {
        ItemCardapio item = new ItemCardapio(
                "Salada",
                "Salada verde",
                new BigDecimal("15.00"),
                true,
                "/foto.jpg"
        );

        item.atualizarPreco(new BigDecimal("18.00"));
        assertEquals(new BigDecimal("18.00"), item.getPreco());
    }

    @Test
    void deveLancarExcecaoAoAtualizarPrecoComValorInvalido() {
        ItemCardapio item = new ItemCardapio(
                "Suco",
                "Suco natural",
                new BigDecimal("8.00"),
                true,
                "/foto.jpg"
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                item.atualizarPreco(BigDecimal.ZERO)
        );
        assertEquals("Preço deve ser maior que zero", exception.getMessage());
    }
}

