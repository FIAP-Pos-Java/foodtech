package br.com.fiap.foodtech.core.adapters.gateways;

import br.com.fiap.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.core.dto.ItemCardapioDataDTO;
import br.com.fiap.foodtech.core.dto.NovoItemCardapioDTO;
import br.com.fiap.foodtech.core.exceptions.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;
import br.com.fiap.foodtech.core.interfaces.IItemCardapioGateway;

import java.util.List;
import java.util.stream.Collectors;

public class ItemCardapioGateway implements IItemCardapioGateway {
    private final IDataStorageSource dataStorageSource;

    private ItemCardapioGateway(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    public static ItemCardapioGateway create(IDataStorageSource dataStorageSource) {
        return new ItemCardapioGateway(dataStorageSource);
    }

    @Override
    public ItemCardapio buscarPorId(Long id) {
        var itemData = dataStorageSource.obterItemCardapioPorId(id);
        if (itemData == null) {
            throw new ItemCardapioNaoEncontradoException("Item do cardápio com ID " + id + " não encontrado");
        }

        return mapToItemCardapio(itemData);
    }

    @Override
    public List<ItemCardapio> buscarTodos(int page, int size) {
        var itensData = dataStorageSource.obterTodosItensCardapio(page, size);
        return itensData.stream()
                .map(this::mapToItemCardapio)
                .collect(Collectors.toList());
    }

    @Override
    public ItemCardapio incluir(ItemCardapio itemCardapio) {
        NovoItemCardapioDTO novoItemDTO = new NovoItemCardapioDTO(
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getDisponibilidadeRestaurante(),
                itemCardapio.getCaminhoFoto()
        );

        var itemSalvo = dataStorageSource.incluirItemCardapio(novoItemDTO);
        return mapToItemCardapio(itemSalvo);
    }

    @Override
    public ItemCardapio atualizar(ItemCardapio itemCardapio) {
        NovoItemCardapioDTO itemAtualizadoDTO = new NovoItemCardapioDTO(
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getDisponibilidadeRestaurante(),
                itemCardapio.getCaminhoFoto()
        );

        var itemAtualizado = dataStorageSource.atualizarItemCardapio(itemCardapio.getId(), itemAtualizadoDTO);
        return mapToItemCardapio(itemAtualizado);
    }

    @Override
    public void deletar(Long id) {
        dataStorageSource.deletarItemCardapio(id);
    }

    private ItemCardapio mapToItemCardapio(ItemCardapioDataDTO itemData) {
        return ItemCardapio.create(
                itemData.id(),
                itemData.nome(),
                itemData.descricao(),
                itemData.preco(),
                itemData.disponibilidadeRestaurante(),
                itemData.caminhoFoto()
        );
    }
}