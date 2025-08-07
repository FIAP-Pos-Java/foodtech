package br.com.fiap.foodtech.foodtech.core.controllers;

import br.com.fiap.foodtech.foodtech.core.domain.usecases.BuscarItemCardapioPorIdUseCase;
import br.com.fiap.foodtech.foodtech.core.domain.usecases.CadastrarItemCardapioUseCase;
import br.com.fiap.foodtech.foodtech.core.domain.usecases.ListarItensCardapioUseCase;
import br.com.fiap.foodtech.foodtech.core.dtos.ItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.itemcardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.ItemCardapioGateway;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;
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

    public ItemCardapioDTO cadastrar(NovoItemCardapioDTO novoItemCardapioDTO) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        var useCase = CadastrarItemCardapioUseCase.create(itemCardapioGateway);

        var item = useCase.run(novoItemCardapioDTO);
        return ItemCardapioPresenter.toDTO(item);
    }

    public ItemCardapioDTO buscarPorId(Long id) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        var useCase = BuscarItemCardapioPorIdUseCase.create(itemCardapioGateway);

        try {
            var item = useCase.run(id);
            return ItemCardapioPresenter.toDTO(item);
        } catch (ItemCardapioNaoEncontradoException e) {
            throw e;
        }
    }

    public List<ItemCardapioDTO> listarTodos(int page, int size) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        var useCase = ListarItensCardapioUseCase.create(itemCardapioGateway);

        var itens = useCase.run(page, size);

        return itens.stream()
                .map(ItemCardapioPresenter::toDTO)
                .collect(Collectors.toList());
    }

}
