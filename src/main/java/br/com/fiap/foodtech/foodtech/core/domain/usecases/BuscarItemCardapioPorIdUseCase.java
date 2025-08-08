package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.exceptions.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IItemCardapioGateway;

public class BuscarItemCardapioPorIdUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private BuscarItemCardapioPorIdUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static BuscarItemCardapioPorIdUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new BuscarItemCardapioPorIdUseCase(itemCardapioGateway);
    }

    public ItemCardapio run(Long id) throws ItemCardapioNaoEncontradoException {
        ItemCardapio item = itemCardapioGateway.buscarPorId(id);

        if (item == null) {
            throw new ItemCardapioNaoEncontradoException("Item do cardápio com ID " + id + " não encontrado");
        }

        return item;
    }

}
