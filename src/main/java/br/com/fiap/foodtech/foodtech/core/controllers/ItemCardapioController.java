package br.com.fiap.foodtech.foodtech.core.controllers;

import br.com.fiap.foodtech.foodtech.core.domain.usecases.*;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.core.exceptions.itemcardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.ItemCardapioGateway;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;
import br.com.fiap.foodtech.foodtech.core.presenters.ClientePresenter;
import br.com.fiap.foodtech.foodtech.core.presenters.ItemCardapioPresenter;

import java.util.List;
import java.util.stream.Collectors;

public class ItemCardapioController {

    private final DataSource dataSource;

    private ItemCardapioController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ItemCardapioController create(DataSource dataSource) {
        return new ItemCardapioController(dataSource);
    }

    public Pagina<ItemCardapioDTO> buscarTodosItensCardapio(Paginacao paginacao) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        var useCase = ListarItensCardapioUseCase.create(itemCardapioGateway);

        var itensCardapio = useCase.run(paginacao);
        return new Pagina<>(itensCardapio.content().stream().map(ItemCardapioPresenter::toDTO).toList(), itensCardapio.totalElements());
    }

    public ItemCardapioDTO buscarPorId(Long id) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        var useCase = BuscarItemCardapioPorIdUseCase.create(itemCardapioGateway);

        var item = useCase.run(id);
        return ItemCardapioPresenter.toDTO(item);
    }

    public ItemCardapioDTO cadastrar(NovoItemCardapioDTO novoItemCardapioDTO) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        var useCase = CadastrarItemCardapioUseCase.create(itemCardapioGateway);

        var item = useCase.run(novoItemCardapioDTO);
        return ItemCardapioPresenter.toDTO(item);
    }

    public ItemCardapioDTO atualizar(Long id, ItemCardapioDataDTO itemCardapioDataDTO) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        var useCase = AtualizarItemCardapioUseCase.create(itemCardapioGateway);

        var item = useCase.run(id, itemCardapioDataDTO);
        return ItemCardapioPresenter.toDTO(item);
    }

    public void deletar(Long id) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        var useCase = DeletarItemCardapioUseCase.create(itemCardapioGateway);

        useCase.run(id);
    }

}
