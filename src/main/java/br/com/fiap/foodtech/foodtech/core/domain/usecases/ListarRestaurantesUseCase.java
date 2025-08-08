package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.dtos.Pagina;
import br.com.fiap.foodtech.foodtech.core.dtos.Paginacao;
import br.com.fiap.foodtech.foodtech.core.gateways.IRestauranteGateway;

public class ListarRestaurantesUseCase {

    private final IRestauranteGateway restauranteGateway;

    private ListarRestaurantesUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static ListarRestaurantesUseCase create(IRestauranteGateway restauranteGateway) {
        return new ListarRestaurantesUseCase(restauranteGateway);
    }

    public Pagina<Restaurante> run(Paginacao paginacao) {
        return restauranteGateway.buscarTodos(paginacao);
    }

}
