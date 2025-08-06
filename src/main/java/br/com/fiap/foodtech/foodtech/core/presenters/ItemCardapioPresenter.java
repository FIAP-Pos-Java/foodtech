package br.com.fiap.foodtech.foodtech.core.presenters;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.dtos.ItemCardapioDTO;

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
