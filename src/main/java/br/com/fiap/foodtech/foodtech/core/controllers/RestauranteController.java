package br.com.fiap.foodtech.foodtech.core.controllers;

import br.com.fiap.foodtech.foodtech.core.domain.usecases.BuscarRestaurantePorIdUseCase;
import br.com.fiap.foodtech.foodtech.core.domain.usecases.CadastrarRestauranteUseCase;
import br.com.fiap.foodtech.foodtech.core.domain.usecases.ListarRestaurantesUseCase;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoRestauranteDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.RestauranteDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.exceptions.restaurante.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.GestorGateway;
import br.com.fiap.foodtech.foodtech.core.gateways.RestauranteGateway;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;
import br.com.fiap.foodtech.foodtech.core.presenters.RestaurantePresenter;

import java.util.List;
import java.util.stream.Collectors;

public class RestauranteController {

    private final DataSource dataSource;

    private RestauranteController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RestauranteController create(DataSource dataSource) {
        return new RestauranteController(dataSource);
    }

    public RestauranteDTO cadastrar(NovoRestauranteDTO novoRestauranteDTO) {
        var restauranteGateway = RestauranteGateway.create(this.dataSource);
        var gestorGateway = GestorGateway.create(this.dataSource);
        var useCase = CadastrarRestauranteUseCase.create(restauranteGateway, gestorGateway);

        try {
            var restaurante = useCase.run(novoRestauranteDTO);
            return RestaurantePresenter.toDTO(restaurante);
        } catch (GestorNaoEncontradoException e) {
            throw e;
        }
    }

    public RestauranteDTO buscarPorId(Long id) {
        var restauranteGateway = RestauranteGateway.create(this.dataSource);
        var useCase = BuscarRestaurantePorIdUseCase.create(restauranteGateway);

        try {
            var restaurante = useCase.run(id);
            return RestaurantePresenter.toDTO(restaurante);
        } catch (RestauranteNaoEncontradoException e) {
            throw e;
        }
    }

    public List<RestauranteDTO> listarTodos(int page, int size) {
        var restauranteGateway = RestauranteGateway.create(this.dataSource);
        var useCase = ListarRestaurantesUseCase.create(restauranteGateway);

        var restaurantes = useCase.run(page, size);

        return restaurantes.stream()
                .map(RestaurantePresenter::toDTO)
                .collect(Collectors.toList());
    }

}
