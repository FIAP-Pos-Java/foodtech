package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.dtos.Pagina;
import br.com.fiap.foodtech.foodtech.core.dtos.Paginacao;
import br.com.fiap.foodtech.foodtech.core.gateways.IItemCardapioGateway;

import java.util.List;

public class ListarItensCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private ListarItensCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static ListarItensCardapioUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new ListarItensCardapioUseCase(itemCardapioGateway);
    }

    public Pagina<ItemCardapio> run(Paginacao paginacao) {
        return itemCardapioGateway.buscarTodos(paginacao);
    }

}
