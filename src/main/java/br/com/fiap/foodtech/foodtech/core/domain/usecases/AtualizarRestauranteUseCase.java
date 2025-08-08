package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.dtos.RestauranteDataDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.exceptions.restaurante.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IGestorGateway;
import br.com.fiap.foodtech.foodtech.core.gateways.IRestauranteGateway;

public class AtualizarRestauranteUseCase {

    private final IRestauranteGateway restauranteGateway;
    private final IGestorGateway gestorGateway;

    private AtualizarRestauranteUseCase(IRestauranteGateway restauranteGateway, IGestorGateway gestorGateway) {
        this.restauranteGateway = restauranteGateway;
        this.gestorGateway = gestorGateway;
    }

    public static AtualizarRestauranteUseCase create(IRestauranteGateway restauranteGateway, IGestorGateway gestorGateway) {
        return new AtualizarRestauranteUseCase(restauranteGateway, gestorGateway);
    }

    public Restaurante run(Long id, RestauranteDataDTO restauranteDataDTO) {
        Restaurante restauranteExistente = restauranteGateway.buscarPorId(id);

        if (restauranteExistente == null) {
            throw new RestauranteNaoEncontradoException("Restaurante com ID " + id + " não encontrado");
        }

        Gestor gestor = gestorGateway.buscarPorId(restauranteDataDTO.gestor().id());

        if (gestor == null) {
            throw new GestorNaoEncontradoException("Gestor com ID " + restauranteDataDTO.gestor().id() + " não encontrado");
        }

        Endereco endereco = new Endereco(
                restauranteExistente.getEndereco().getId(),
                restauranteDataDTO.endereco().logradouro(),
                restauranteDataDTO.endereco().numero(),
                restauranteDataDTO.endereco().bairro(),
                restauranteDataDTO.endereco().cidade(),
                restauranteDataDTO.endereco().estado(),
                restauranteDataDTO.endereco().cep()
        );

        Restaurante restauranteAtualizado = new Restaurante(
                restauranteExistente.getId(),
                restauranteDataDTO.nome(),
                restauranteDataDTO.tipoCozinha(),
                restauranteDataDTO.horarioAbertura(),
                restauranteDataDTO.horarioFechamento(),
                endereco,
                gestor
        );
        return restauranteGateway.atualizar(restauranteAtualizado);
    }

}
