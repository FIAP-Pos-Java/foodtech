package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.dtos.ItemCardapioDataDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.itemcardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IItemCardapioGateway;

public class AtualizarItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private AtualizarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static AtualizarItemCardapioUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new AtualizarItemCardapioUseCase(itemCardapioGateway);
    }

    public ItemCardapio run(Long id, ItemCardapioDataDTO itemCardapioDataDTO) {
        ItemCardapio itemCardapio = itemCardapioGateway.buscarPorId(id);

        if (itemCardapio == null) {
            throw new ItemCardapioNaoEncontradoException("Item do cardápio com ID " + id + " não encontrado");
        }
        
        ItemCardapio itemAtualizado = new ItemCardapio(
                itemCardapio.getId(),
                itemCardapioDataDTO.nome(),
                itemCardapioDataDTO.descricao(),
                itemCardapioDataDTO.preco(),
                itemCardapioDataDTO.disponibilidadeRestaurante(),
                itemCardapioDataDTO.caminhoFoto()
        );

        return itemCardapioGateway.atualizar(itemAtualizado);
    }

}
