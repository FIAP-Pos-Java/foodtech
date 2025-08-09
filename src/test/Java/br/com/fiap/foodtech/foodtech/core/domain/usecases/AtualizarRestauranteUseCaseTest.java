package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.domain.helper.RestauranteHelper;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.exceptions.restaurante.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IGestorGateway;
import br.com.fiap.foodtech.foodtech.core.gateways.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.BlockingDeque;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarRestauranteUseCaseTest {

    private IRestauranteGateway restauranteGateway;
    private IGestorGateway gestorGateway;
    private AtualizarRestauranteUseCase useCase;

    @BeforeEach
    void setUp() {
        restauranteGateway = mock(IRestauranteGateway.class);
        gestorGateway = mock(IGestorGateway.class);
        useCase = AtualizarRestauranteUseCase.create(restauranteGateway, gestorGateway);
    }

    @Test
    void deveAtualizarRestauranteComSucesso() {
        Long restauranteId = 1L;
        Long gestorId = 1L;

        Restaurante restauranteExistente = RestauranteHelper.gerarRestaurante();


        Gestor gestor = RestauranteHelper.gerarGestor();

        RestauranteDataDTO dto = new RestauranteDataDTO(1L,
                "Restaurante B",
                "Japonesa",
                LocalTime.of(11,01),
                LocalTime.of(11,03),
                new GestorDataDTO(1L,
                        "Maria Oliveira",
                        "maria@email.com",
                        "GESTOR",
                        new LoginDataDTO(1L, "senhaSegura"),
                        new EnderecoDataDTO(1L, "Rua B", "456", "Bairro", "Rio de Janeiro", "RJ", "98765-432")),
                new EnderecoDataDTO(1L, "Rua B", "456", "Bairro", "Rio de Janeiro", "RJ", "98765-432"));

        Restaurante restauranteAtualizado = new Restaurante(
                restauranteId,
                dto.nome(),
                dto.tipoCozinha(),
                dto.horarioAbertura(),
                dto.horarioFechamento(),
                new Endereco(restauranteExistente.getEndereco().getId(),
                        dto.endereco().logradouro(),
                        dto.endereco().numero(),
                        dto.endereco().bairro(),
                        dto.endereco().cidade(),
                        dto.endereco().estado(),
                        dto.endereco().cep()),
                gestor
        );

        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(restauranteExistente);
        when(gestorGateway.buscarPorId(gestorId)).thenReturn(gestor);
        when(restauranteGateway.atualizar(any(Restaurante.class))).thenReturn(restauranteAtualizado);

        Restaurante resultado = useCase.run(restauranteId, dto);

        assertEquals(dto.nome(), resultado.getNome());
        assertEquals(dto.tipoCozinha(), resultado.getTipoCozinha());
        assertEquals(dto.horarioAbertura(), resultado.getHorarioAbertura());
        assertEquals(dto.horarioFechamento(), resultado.getHorarioFechamento());
        assertEquals(dto.endereco().logradouro(), resultado.getEndereco().getLogradouro());
        assertEquals(dto.gestor().id(), resultado.getGestor().getId());
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long restauranteId = 99L;
        RestauranteDataDTO dto = new RestauranteDataDTO(1L,
                "Restaurante B",
                "Japonesa",
                LocalTime.of(11,01),
                LocalTime.of(11,03),
                new GestorDataDTO(1L,
                        "Maria Oliveira",
                        "maria@email.com",
                        "GESTOR",
                        new LoginDataDTO(1L, "senhaSegura"),
                        new EnderecoDataDTO(1L, "Rua B", "456", "Bairro", "Rio de Janeiro", "RJ", "98765-432")),
                new EnderecoDataDTO(1L, "Rua B", "456", "Bairro", "Rio de Janeiro", "RJ", "98765-432"));



        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(null);

        assertThrows(RestauranteNaoEncontradoException.class, () -> useCase.run(restauranteId, dto));
    }

    @Test
    void deveLancarExcecaoQuandoGestorNaoEncontrado() {
        Long restauranteId = 1L;
        Long gestorId = 99L;

        Restaurante restauranteExistente = RestauranteHelper.gerarRestaurante();

        RestauranteDataDTO dto = new RestauranteDataDTO(1L,
                "Restaurante B",
                "Japonesa",
                LocalTime.of(11,01),
                LocalTime.of(11,03),
                new GestorDataDTO(1L,
                        "Maria Oliveira",
                        "maria@email.com",
                        "GESTOR",
                        new LoginDataDTO(1L, "senhaSegura"),
                        new EnderecoDataDTO(1L, "Rua B", "456", "Bairro", "Rio de Janeiro", "RJ", "98765-432")),
                new EnderecoDataDTO(1L, "Rua B", "456", "Bairro", "Rio de Janeiro", "RJ", "98765-432"));



        when(restauranteGateway.buscarPorId(restauranteId)).thenReturn(restauranteExistente);
        when(gestorGateway.buscarPorId(gestorId)).thenReturn(null);

        assertThrows(GestorNaoEncontradoException.class, () -> useCase.run(restauranteId, dto));
    }
}


