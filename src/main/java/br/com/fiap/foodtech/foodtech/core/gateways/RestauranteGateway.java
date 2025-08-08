package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
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
            return null;
        }

        return mapToRestaurante(restauranteData);
    }

    @Override
    public Pagina<Restaurante> buscarTodos(Paginacao paginacao) {
        var paginaRestaurante = dataSource.obterTodosRestaurantes(paginacao);
        return new Pagina<>(paginaRestaurante.content().stream().map(restauranteDTO -> mapToRestaurante(restauranteDTO)).toList(),
                paginaRestaurante.totalElements());
    }

    @Override
    public Restaurante incluir(Restaurante restaurante) {
        NovoRestauranteComGestorDTO novoRestauranteDTO = new NovoRestauranteComGestorDTO(
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                new GestorDataDTO(
                        restaurante.getGestor().getId(),
                        restaurante.getGestor().getNome(),
                        restaurante.getGestor().getEmail(),
                        restaurante.getGestor().getTipoUsuario(),
                        new LoginDataDTO(
                                restaurante.getGestor().getLogin().getId(),
                                restaurante.getGestor().getLogin().getLogin()
                        ),
                        new EnderecoDataDTO(
                                restaurante.getGestor().getEndereco().getId(),
                                restaurante.getGestor().getEndereco().getLogradouro(),
                                restaurante.getGestor().getEndereco().getNumero(),
                                restaurante.getGestor().getEndereco().getBairro(),
                                restaurante.getGestor().getEndereco().getCidade(),
                                restaurante.getGestor().getEndereco().getEstado(),
                                restaurante.getGestor().getEndereco().getCep()
                        )
                ),
                new NovoEnderecoDTO(
                        restaurante.getEndereco().getLogradouro(),
                        restaurante.getEndereco().getNumero(),
                        restaurante.getEndereco().getBairro(),
                        restaurante.getEndereco().getCidade(),
                        restaurante.getEndereco().getEstado(),
                        restaurante.getEndereco().getCep()
                )
        );

        var restauranteSalvo = dataSource.incluirRestaurante(novoRestauranteDTO);
        return mapToRestaurante(restauranteSalvo);
    }

    @Override
    public Restaurante atualizar(Restaurante restaurante) {

        LoginDataDTO loginGestorDTO = new LoginDataDTO(
                restaurante.getGestor().getLogin().getId(),
                restaurante.getGestor().getLogin().getLogin()
        );

        EnderecoDataDTO enderecoGestorDTO = new EnderecoDataDTO(
                restaurante.getGestor().getEndereco().getId(),
                restaurante.getGestor().getEndereco().getLogradouro(),
                restaurante.getGestor().getEndereco().getNumero(),
                restaurante.getGestor().getEndereco().getBairro(),
                restaurante.getGestor().getEndereco().getCidade(),
                restaurante.getGestor().getEndereco().getEstado(),
                restaurante.getGestor().getEndereco().getCep()
        );

        GestorDataDTO gestorDTO = new GestorDataDTO(
                restaurante.getGestor().getId(),
                restaurante.getGestor().getNome(),
                restaurante.getGestor().getEmail(),
                restaurante.getGestor().getTipoUsuario(),
                loginGestorDTO,
                enderecoGestorDTO
        );

        EnderecoDataDTO enderecoRestauranteDTO = new EnderecoDataDTO(
                restaurante.getEndereco().getId(),
                restaurante.getEndereco().getLogradouro(),
                restaurante.getEndereco().getNumero(),
                restaurante.getEndereco().getBairro(),
                restaurante.getEndereco().getCidade(),
                restaurante.getEndereco().getEstado(),
                restaurante.getEndereco().getCep()
        );

        RestauranteDataDTO restauranteDataDTO = new RestauranteDataDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                gestorDTO,
                enderecoRestauranteDTO
        );

        var restauranteAtualizado = dataSource.atualizarRestaurante(restauranteDataDTO);
        return mapToRestaurante(restauranteAtualizado);
    }

    @Override
    public void deletar(Long id) {
        dataSource.deletarRestaurante(id);
    }

    private Restaurante mapToRestaurante(RestauranteDataDTO restauranteData) {

        Login loginGestor = new Login(
                restauranteData.gestor().login().id(),
                restauranteData.gestor().login().login()
        );

        Endereco enderecoGestor = new Endereco(
                restauranteData.gestor().endereco().logradouro(),
                restauranteData.gestor().endereco().numero(),
                restauranteData.gestor().endereco().bairro(),
                restauranteData.gestor().endereco().cidade(),
                restauranteData.gestor().endereco().estado(),
                restauranteData.gestor().endereco().cep()
        );

        Gestor gestor = new Gestor(
                restauranteData.gestor().id(),
                restauranteData.gestor().nome(),
                restauranteData.gestor().email(),
                restauranteData.gestor().tipoUsuario(),
                enderecoGestor,
                loginGestor
        );

        Endereco endereco = new Endereco(
                restauranteData.endereco().logradouro(),
                restauranteData.endereco().numero(),
                restauranteData.endereco().bairro(),
                restauranteData.endereco().cidade(),
                restauranteData.endereco().estado(),
                restauranteData.endereco().cep()
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
