package br.com.fiap.foodtech.core.adapters.controllers;

import br.com.fiap.foodtech.core.adapters.gateways.ItemCardapioGateway;
import br.com.fiap.foodtech.core.adapters.presenters.ItemCardapioPresenter;
import br.com.fiap.foodtech.core.domain.usecases.BuscarItemCardapioPorIdUseCase;
import br.com.fiap.foodtech.core.domain.usecases.CadastrarItemCardapioUseCase;
import br.com.fiap.foodtech.core.domain.usecases.ListarItensCardapioUseCase;
import br.com.fiap.foodtech.core.dto.ItemCardapioDTO;
import br.com.fiap.foodtech.core.dto.NovoItemCardapioDTO;
import br.com.fiap.foodtech.core.exceptions.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;

import java.util.List;
import java.util.stream.Collectors;

public class ItemCardapioController {
    private final IDataStorageSource dataStorageSource;

    private ItemCardapioController(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    public static ItemCardapioController create(IDataStorageSource dataStorageSource) {
        return new ItemCardapioController(dataStorageSource);
    }

    public ItemCardapioDTO cadastrar(NovoItemCardapioDTO novoItemCardapioDTO) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataStorageSource);
        var useCase = CadastrarItemCardapioUseCase.create(itemCardapioGateway);

        var item = useCase.run(novoItemCardapioDTO);
        return ItemCardapioPresenter.toDTO(item);
    }

    public ItemCardapioDTO buscarPorId(Long id) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataStorageSource);
        var useCase = BuscarItemCardapioPorIdUseCase.create(itemCardapioGateway);

        try {
            var item = useCase.run(id);
            return ItemCardapioPresenter.toDTO(item);
        } catch (ItemCardapioNaoEncontradoException e) {
            throw e;
        }
    }

    public List<ItemCardapioDTO> listarTodos(int page, int size) {
        var itemCardapioGateway = ItemCardapioGateway.create(this.dataStorageSource);
        var useCase = ListarItensCardapioUseCase.create(itemCardapioGateway);

        var itens = useCase.run(page, size);
        return itens.stream()
                .map(ItemCardapioPresenter::toDTO)
                .collect(Collectors.toList());
    }
}