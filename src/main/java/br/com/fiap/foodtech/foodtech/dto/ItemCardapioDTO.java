package br.com.fiap.foodtech.foodtech.dto;

import br.com.fiap.foodtech.foodtech.entities.ItemCardapio;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Builder
public record ItemCardapioDTO(

        @NotEmpty(message = "É necessário informar um nome para o prato")
        String nome,

        @NotEmpty(message = "É necessário informar uma descrição")
        String descricao,

        @NotNull(message = "É necessario informar o preço do prato")
        BigDecimal preco,

        @NotNull(message = "É necessario informar se existe disponibilidade apenas no restaurante")
        Boolean disponibilidadeRestaurante,

        @NotEmpty(message = "É necessario informar o caminho da foto do prato")
        String caminhoFoto) {

    public ItemCardapio mapearItemCardapio() {
        return new ItemCardapio(nome, descricao, preco, disponibilidadeRestaurante, caminhoFoto);
    }
}
