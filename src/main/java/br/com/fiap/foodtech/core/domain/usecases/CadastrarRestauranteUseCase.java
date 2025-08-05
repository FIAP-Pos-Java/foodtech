package br.com.fiap.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.core.dto.NovoRestauranteDTO;
import br.com.fiap.foodtech.core.exceptions.GestorNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IGestorGateway;
import br.com.fiap.foodtech.core.interfaces.IRestauranteGateway;

public class CadastrarRestauranteUseCase {
    private final IRestauranteGateway restauranteGateway;
    private final IGestorGateway gestorGateway;

    private CadastrarRestauranteUseCase(IRestauranteGateway restauranteGateway, IGestorGateway gestorGateway) {
        this.restauranteGateway = restauranteGateway;
        this.gestorGateway = gestorGateway;
    }

    public static CadastrarRestauranteUseCase create(IRestauranteGateway restauranteGateway, IGestorGateway gestorGateway) {
        return new CadastrarRestauranteUseCase(restauranteGateway, gestorGateway);
    }

    public Restaurante run(NovoRestauranteDTO novoRestauranteDTO) throws GestorNaoEncontradoException {

        Gestor gestor = gestorGateway.buscarPorId(novoRestauranteDTO.idGestor());
        if (gestor == null) {
            throw new GestorNaoEncontradoException("Gestor com ID " + novoRestauranteDTO.idGestor() + " não encontrado");
        }


        Endereco endereco = Endereco.create(
                novoRestauranteDTO.logradouro(),
                novoRestauranteDTO.numero(),
                novoRestauranteDTO.bairro(),
                novoRestauranteDTO.cidade(),
                novoRestauranteDTO.estado(),
                novoRestauranteDTO.cep()
        );


        Restaurante novoRestaurante = Restaurante.create(
                novoRestauranteDTO.nome(),
                novoRestauranteDTO.tipoCozinha(),
                novoRestauranteDTO.horarioAbertura(),
                novoRestauranteDTO.horarioFechamento(),
                gestor,
                endereco
        );

        return restauranteGateway.incluir(novoRestaurante);
    }
}