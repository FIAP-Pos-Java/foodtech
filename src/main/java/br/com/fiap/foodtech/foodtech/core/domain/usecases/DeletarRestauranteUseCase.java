package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.exceptions.cliente.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.exceptions.restaurante.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IRestauranteGateway;
import br.com.fiap.foodtech.foodtech.core.gateways.IRestauranteGateway;

public class DeletarRestauranteUseCase {

    private final IRestauranteGateway restauranteGateway;

    private DeletarRestauranteUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static DeletarRestauranteUseCase create(IRestauranteGateway restauranteGateway) {
        return new DeletarRestauranteUseCase(restauranteGateway);
    }

    public void run(Long id) {
        Restaurante restauranteExistente = restauranteGateway.buscarPorId(id);

        if (restauranteExistente == null) {
            throw new RestauranteNaoEncontradoException("Restaurante com ID " + id + " não encontrado");
        }
        restauranteGateway.deletar(id);
    }
}
