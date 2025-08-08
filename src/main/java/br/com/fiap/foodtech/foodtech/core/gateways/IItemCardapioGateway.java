package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.dtos.Pagina;
import br.com.fiap.foodtech.foodtech.core.dtos.Paginacao;

import java.util.List;

public interface IItemCardapioGateway {

    ItemCardapio buscarPorId(Long id);

    Pagina<ItemCardapio> buscarTodos(Paginacao paginacao);

    ItemCardapio incluir(ItemCardapio itemCardapio);

    ItemCardapio atualizar(ItemCardapio itemCardapio);

    void deletar(Long id);

}
