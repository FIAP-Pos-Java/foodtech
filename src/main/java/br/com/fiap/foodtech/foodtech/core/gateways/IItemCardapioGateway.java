package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;

import java.util.List;

public interface IItemCardapioGateway {

    ItemCardapio buscarPorId(Long id);

    List<ItemCardapio> buscarTodos(int page, int size);

    ItemCardapio incluir(ItemCardapio itemCardapio);

    ItemCardapio atualizar(ItemCardapio itemCardapio);

    void deletar(Long id);

}
