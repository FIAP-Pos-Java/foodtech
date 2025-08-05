
package br.com.fiap.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.core.interfaces.IItemCardapioGateway;

import java.util.List;

public class ListarItensCardapioUseCase {
    private final IItemCardapioGateway itemCardapioGateway;

    private ListarItensCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static ListarItensCardapioUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new ListarItensCardapioUseCase(itemCardapioGateway);
    }

    public List<ItemCardapio> run(int page, int size) {
        return itemCardapioGateway.buscarTodos(page, size);
    }
}