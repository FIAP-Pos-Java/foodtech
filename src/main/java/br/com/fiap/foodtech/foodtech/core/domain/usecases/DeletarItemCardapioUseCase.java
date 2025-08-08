package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.exceptions.itemcardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IItemCardapioGateway;

public class DeletarItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private DeletarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static DeletarItemCardapioUseCase create(IItemCardapioGateway clienteGateway) {
        return new DeletarItemCardapioUseCase(clienteGateway);
    }

    public void run(Long id) {
        ItemCardapio itemCardapioExistente = itemCardapioGateway.buscarPorId(id);

        if (itemCardapioExistente == null) {
            throw new ItemCardapioNaoEncontradoException("Item do cardápio com ID " + id + " não encontrado");
        }
        itemCardapioGateway.deletar(id);
    }
}
