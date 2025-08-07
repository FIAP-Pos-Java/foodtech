package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoRestauranteDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.RestauranteDataDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.restaurante.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;

import java.util.List;
import java.util.stream.Collectors;

public class RestauranteGateway implements IRestauranteGateway {

    private final DataSource dataSource;

    private RestauranteGateway(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RestauranteGateway create(DataSource dataStorageSource) {
        return new RestauranteGateway(dataStorageSource);
    }

    @Override
    public Restaurante buscarPorId(Long id) {
        var restauranteData = dataSource.obterRestaurantePorId(id);
        if (restauranteData == null) {
            throw new RestauranteNaoEncontradoException("Restaurante com ID " + id + " não encontrado");
        }

        return mapToRestaurante(restauranteData);
    }

    @Override
    public List<Restaurante> buscarTodos(int page, int size) {
        var restaurantesData = dataSource.obterTodosRestaurantes(page, size);
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

        var restauranteSalvo = dataSource.incluirRestaurante(novoRestauranteDTO);
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

        var restauranteAtualizado = dataSource.atualizarRestaurante(restaurante.getId(), restauranteAtualizadoDTO);
        return mapToRestaurante(restauranteAtualizado);
    }

    @Override
    public void deletar(Long id) {
        dataSource.deletarRestaurante(id);
    }

    private Restaurante mapToRestaurante(RestauranteDataDTO restauranteData) {

        Login loginGestor = new Login(
                restauranteData.gestorData().loginData().login(),
                restauranteData.gestorData().loginData().senha()
        );


        Endereco enderecoGestor = new Endereco(
                restauranteData.gestorData().enderecoData().logradouro(),
                restauranteData.gestorData().enderecoData().numero(),
                restauranteData.gestorData().enderecoData().bairro(),
                restauranteData.gestorData().enderecoData().cidade(),
                restauranteData.gestorData().enderecoData().estado(),
                restauranteData.gestorData().enderecoData().cep()
        );


        Gestor gestor = new Gestor(
                restauranteData.gestorData().id(),
                restauranteData.gestorData().nome(),
                restauranteData.gestorData().email(),
                restauranteData.gestorData().tipoUsuario(),
                enderecoGestor,
                loginGestor
        );


        Endereco endereco = new Endereco(
                restauranteData.enderecoData().logradouro(),
                restauranteData.enderecoData().numero(),
                restauranteData.enderecoData().bairro(),
                restauranteData.enderecoData().cidade(),
                restauranteData.enderecoData().estado(),
                restauranteData.enderecoData().cep()
        );


        return new Restaurante(
                restauranteData.id(),
                restauranteData.nome(),
                restauranteData.tipoCozinha(),
                restauranteData.horarioAbertura(),
                restauranteData.horarioFechamento(),
                endereco,
                gestor
        );
    }

}
