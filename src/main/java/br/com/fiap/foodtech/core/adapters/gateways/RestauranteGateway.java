package br.com.fiap.foodtech.core.adapters.gateways;

import br.com.fiap.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.core.dto.NovoRestauranteDTO;
import br.com.fiap.foodtech.core.exceptions.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;
import br.com.fiap.foodtech.core.interfaces.IRestauranteGateway;

import java.util.List;
import java.util.stream.Collectors;

public class RestauranteGateway implements IRestauranteGateway {
    private final IDataStorageSource dataStorageSource;

    private RestauranteGateway(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    public static RestauranteGateway create(IDataStorageSource dataStorageSource) {
        return new RestauranteGateway(dataStorageSource);
    }

    @Override
    public Restaurante buscarPorId(Long id) {
        var restauranteData = dataStorageSource.obterRestaurantePorId(id);
        if (restauranteData == null) {
            throw new RestauranteNaoEncontradoException("Restaurante com ID " + id + " não encontrado");
        }

        return mapToRestaurante(restauranteData);
    }

    @Override
    public List<Restaurante> buscarTodos(int page, int size) {
        var restaurantesData = dataStorageSource.obterTodosRestaurantes(page, size);
        return restaurantesData.stream()
                .map(this::mapToRestaurante)
                .collect(Collectors.toList());
    }

    @Override
    public Restaurante incluir(Restaurante restaurante) {
        NovoRestauranteDTO novoRestauranteDTO = new NovoRestauranteDTO(
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                restaurante.getGestor().getId(),
                restaurante.getEndereco().getLogradouro(),
                restaurante.getEndereco().getNumero(),
                restaurante.getEndereco().getBairro(),
                restaurante.getEndereco().getCidade(),
                restaurante.getEndereco().getEstado(),
                restaurante.getEndereco().getCep()
        );

        var restauranteSalvo = dataStorageSource.incluirRestaurante(novoRestauranteDTO);
        return mapToRestaurante(restauranteSalvo);
    }

    @Override
    public Restaurante atualizar(Restaurante restaurante) {
        NovoRestauranteDTO restauranteAtualizadoDTO = new NovoRestauranteDTO(
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                restaurante.getGestor().getId(),
                restaurante.getEndereco().getLogradouro(),
                restaurante.getEndereco().getNumero(),
                restaurante.getEndereco().getBairro(),
                restaurante.getEndereco().getCidade(),
                restaurante.getEndereco().getEstado(),
                restaurante.getEndereco().getCep()
        );

        var restauranteAtualizado = dataStorageSource.atualizarRestaurante(restaurante.getId(), restauranteAtualizadoDTO);
        return mapToRestaurante(restauranteAtualizado);
    }

    @Override
    public void deletar(Long id) {
        dataStorageSource.deletarRestaurante(id);
    }

    private Restaurante mapToRestaurante(br.com.fiap.foodtech.core.dto.RestauranteDataDTO restauranteData) {

        Login loginGestor = Login.create(
                restauranteData.gestorData().loginData().login(),
                restauranteData.gestorData().loginData().senha()
        );


        Endereco enderecoGestor = Endereco.create(
                restauranteData.gestorData().enderecoData().logradouro(),
                restauranteData.gestorData().enderecoData().numero(),
                restauranteData.gestorData().enderecoData().bairro(),
                restauranteData.gestorData().enderecoData().cidade(),
                restauranteData.gestorData().enderecoData().estado(),
                restauranteData.gestorData().enderecoData().cep()
        );


        Gestor gestor = Gestor.create(
                restauranteData.gestorData().id(),
                restauranteData.gestorData().nome(),
                restauranteData.gestorData().email(),
                restauranteData.gestorData().tipoUsuario(),
                loginGestor,
                enderecoGestor
        );


        Endereco endereco = Endereco.create(
                restauranteData.enderecoData().logradouro(),
                restauranteData.enderecoData().numero(),
                restauranteData.enderecoData().bairro(),
                restauranteData.enderecoData().cidade(),
                restauranteData.enderecoData().estado(),
                restauranteData.enderecoData().cep()
        );


        return Restaurante.create(
                restauranteData.id(),
                restauranteData.nome(),
                restauranteData.tipoCozinha(),
                restauranteData.horarioAbertura(),
                restauranteData.horarioFechamento(),
                gestor,
                endereco
        );
    }
}