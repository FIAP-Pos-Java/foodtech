package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.core.gateways.IItemCardapioGateway;

public class CadastrarItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private CadastrarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static CadastrarItemCardapioUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new CadastrarItemCardapioUseCase(itemCardapioGateway);
    }

    public ItemCardapio run(NovoItemCardapioDTO novoItemDTO) {
        ItemCardapio novoItem = new ItemCardapio(
                novoItemDTO.nome(),
                novoItemDTO.descricao(),
                novoItemDTO.preco(),
                novoItemDTO.disponibilidadeRestaurante(),
                novoItemDTO.caminhoFoto()
        );

        return itemCardapioGateway.incluir(novoItem);
    }

}
