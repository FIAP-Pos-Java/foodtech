package br.com.fiap.foodtech.foodtech.core.controllers;

import br.com.fiap.foodtech.foodtech.core.domain.usecases.*;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.exceptions.restaurante.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.ClienteGateway;
import br.com.fiap.foodtech.foodtech.core.gateways.GestorGateway;
import br.com.fiap.foodtech.foodtech.core.gateways.RestauranteGateway;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;
import br.com.fiap.foodtech.foodtech.core.presenters.ClientePresenter;
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

        var restaurante = useCase.run(novoRestauranteDTO);
        return RestaurantePresenter.toDTO(restaurante);
    }

    public RestauranteDTO atualizar(Long id, RestauranteDataDTO restauranteDataDTO) {
        var restauranteGateway = RestauranteGateway.create(this.dataSource);
        var gestorGateway = GestorGateway.create(this.dataSource);
        var useCase = AtualizarRestauranteUseCase.create(restauranteGateway, gestorGateway);

        var restaurante = useCase.run(id, restauranteDataDTO);
        return RestaurantePresenter.toDTO(restaurante);
    }

    public RestauranteDTO buscarPorId(Long id) {
        var restauranteGateway = RestauranteGateway.create(this.dataSource);
        var useCase = BuscarRestaurantePorIdUseCase.create(restauranteGateway);

        var restaurante = useCase.run(id);
        return RestaurantePresenter.toDTO(restaurante);
    }

    public Pagina<RestauranteDTO> buscarTodosRestaurantes(Paginacao paginacao) {
        var restauranteGateway = RestauranteGateway.create(this.dataSource);
        var useCase = ListarRestaurantesUseCase.create(restauranteGateway);

        var paginaRestaurante = useCase.run(paginacao);
        return new Pagina<>(paginaRestaurante.content().stream().map(RestaurantePresenter::toDTO).toList(), paginaRestaurante.totalElements());
    }

    public void deletar(Long id) {
        var restauranteGateway = RestauranteGateway.create(this.dataSource);
        var useCase = DeletarRestauranteUseCase.create(restauranteGateway);

        useCase.run(id);
    }

}
