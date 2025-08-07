package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.dtos.ItemCardapioDataDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.itemcardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;

import java.util.List;
import java.util.stream.Collectors;

public class ItemCardapioGateway implements IItemCardapioGateway {

    private final DataSource dataSource;

    private ItemCardapioGateway(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ItemCardapioGateway create(DataSource dataStorageSource) {
        return new ItemCardapioGateway(dataStorageSource);
    }

    @Override
    public ItemCardapio buscarPorId(Long id) {
        var itemData = dataSource.obterItemCardapioPorId(id);
        if (itemData == null) {
            throw new ItemCardapioNaoEncontradoException("Item do cardápio com ID " + id + " não encontrado");
        }

        return mapToItemCardapio(itemData);
    }

    @Override
    public List<ItemCardapio> buscarTodos(int page, int size) {
        var itensData = dataSource.obterTodosItensCardapio(page, size);
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

        var itemSalvo = dataSource.incluirItemCardapio(novoItemDTO);
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

        var itemAtualizado = dataSource.atualizarItemCardapio(itemCardapio.getId(), itemAtualizadoDTO);
        return mapToItemCardapio(itemAtualizado);
    }

    @Override
    public void deletar(Long id) {
        dataSource.deletarItemCardapio(id);
    }

    private ItemCardapio mapToItemCardapio(ItemCardapioDataDTO itemData) {
        return new ItemCardapio(
                itemData.id(),
                itemData.nome(),
                itemData.descricao(),
                itemData.preco(),
                itemData.disponibilidadeRestaurante(),
                itemData.caminhoFoto()
        );
    }

}
