package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.exceptions.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IRestauranteGateway;

public class BuscarRestaurantePorIdUseCase {

    private final IRestauranteGateway restauranteGateway;

    private BuscarRestaurantePorIdUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static BuscarRestaurantePorIdUseCase create(IRestauranteGateway restauranteGateway) {
        return new BuscarRestaurantePorIdUseCase(restauranteGateway);
    }

    public Restaurante run(Long id) throws RestauranteNaoEncontradoException {
        Restaurante restaurante = restauranteGateway.buscarPorId(id);

        if (restaurante == null) {
            throw new RestauranteNaoEncontradoException("Restaurante com ID " + id + " não encontrado");
        }

        return restaurante;
    }

}
