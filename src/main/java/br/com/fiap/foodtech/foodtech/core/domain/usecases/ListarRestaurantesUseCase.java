package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.gateways.IRestauranteGateway;

import java.util.List;

public class ListarRestaurantesUseCase {

    private final IRestauranteGateway restauranteGateway;

    private ListarRestaurantesUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static ListarRestaurantesUseCase create(IRestauranteGateway restauranteGateway) {
        return new ListarRestaurantesUseCase(restauranteGateway);
    }

    public List<Restaurante> run(int page, int size) {
        return restauranteGateway.buscarTodos(page, size);
    }

}
