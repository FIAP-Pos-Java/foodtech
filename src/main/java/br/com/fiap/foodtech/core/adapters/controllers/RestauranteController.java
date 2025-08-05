package br.com.fiap.foodtech.core.adapters.controllers;

import br.com.fiap.foodtech.core.adapters.gateways.GestorGateway;
import br.com.fiap.foodtech.core.adapters.gateways.RestauranteGateway;
import br.com.fiap.foodtech.core.adapters.presenters.RestaurantePresenter;
import br.com.fiap.foodtech.core.domain.usecases.BuscarRestaurantePorIdUseCase;
import br.com.fiap.foodtech.core.domain.usecases.CadastrarRestauranteUseCase;
import br.com.fiap.foodtech.core.domain.usecases.ListarRestaurantesUseCase;
import br.com.fiap.foodtech.core.dto.NovoRestauranteDTO;
import br.com.fiap.foodtech.core.dto.RestauranteDTO;
import br.com.fiap.foodtech.core.exceptions.GestorNaoEncontradoException;
import br.com.fiap.foodtech.core.exceptions.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;

import java.util.List;
import java.util.stream.Collectors;

public class RestauranteController {
    private final IDataStorageSource dataStorageSource;

    private RestauranteController(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    public static RestauranteController create(IDataStorageSource dataStorageSource) {
        return new RestauranteController(dataStorageSource);
    }

    public RestauranteDTO cadastrar(NovoRestauranteDTO novoRestauranteDTO) {
        var restauranteGateway = RestauranteGateway.create(this.dataStorageSource);
        var gestorGateway = GestorGateway.create(this.dataStorageSource);
        var useCase = CadastrarRestauranteUseCase.create(restauranteGateway, gestorGateway);

        try {
            var restaurante = useCase.run(novoRestauranteDTO);
            return RestaurantePresenter.toDTO(restaurante);
        } catch (GestorNaoEncontradoException e) {
            throw e;
        }
    }

    public RestauranteDTO buscarPorId(Long id) {
        var restauranteGateway = RestauranteGateway.create(this.dataStorageSource);
        var useCase = BuscarRestaurantePorIdUseCase.create(restauranteGateway);

        try {
            var restaurante = useCase.run(id);
            return RestaurantePresenter.toDTO(restaurante);
        } catch (RestauranteNaoEncontradoException e) {
            throw e;
        }
    }

    public List<RestauranteDTO> listarTodos(int page, int size) {
        var restauranteGateway = RestauranteGateway.create(this.dataStorageSource);
        var useCase = ListarRestaurantesUseCase.create(restauranteGateway);

        var restaurantes = useCase.run(page, size);
        return restaurantes.stream()
                .map(RestaurantePresenter::toDTO)
                .collect(Collectors.toList());
    }
}