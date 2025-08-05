package br.com.fiap.foodtech.core.adapters.presenters;

import br.com.fiap.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.core.dto.ItemCardapioDTO;

public class ItemCardapioPresenter {

    public static ItemCardapioDTO toDTO(ItemCardapio itemCardapio) {
        return new ItemCardapioDTO(
                itemCardapio.getId(),
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getDisponibilidadeRestaurante(),
                itemCardapio.getCaminhoFoto()
        );
    }
}