package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.domain.helper.RestauranteHelper;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoRestauranteDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IGestorGateway;
import br.com.fiap.foodtech.foodtech.core.gateways.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastrarRestauranteUseCaseTest {

    private IRestauranteGateway restauranteGateway;
    private IGestorGateway gestorGateway;
    private CadastrarRestauranteUseCase useCase;

    @BeforeEach
    void setUp() {
        restauranteGateway = mock(IRestauranteGateway.class);
        gestorGateway = mock(IGestorGateway.class);
        useCase = CadastrarRestauranteUseCase.create(restauranteGateway, gestorGateway);
    }

    @Test
    void deveCadastrarRestauranteComGestorExistente() throws GestorNaoEncontradoException {
        // Arrange
        Long idGestor = 1L;
        Gestor gestor = RestauranteHelper.gerarGestor();
        NovoRestauranteDTO dto = RestauranteHelper.gerarNovoRestauranteDTO();

        Restaurante restauranteEsperado = new Restaurante(
                dto.nome(),
                dto.tipoCozinha(),
                dto.horarioAbertura(),
                dto.horarioFechamento(),
                new Endereco(dto.logradouro(), dto.numero(), dto.bairro(), dto.cidade(), dto.estado(), dto.cep()),
                gestor
        );

        when(gestorGateway.buscarPorId(idGestor)).thenReturn(gestor);
        when(restauranteGateway.incluir(any(Restaurante.class))).thenReturn(restauranteEsperado);

        // Act
        Restaurante resultado = useCase.run(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(dto.nome(), resultado.getNome());
        assertEquals(dto.tipoCozinha(), resultado.getTipoCozinha());
        assertEquals(gestor.getId(), resultado.getGestor().getId());

        verify(gestorGateway).buscarPorId(idGestor);
        verify(restauranteGateway).incluir(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecaoQuandoGestorNaoExiste() {
        // Arrange
        Long idGestor = 1L;
        Gestor gestor = RestauranteHelper.gerarGestor();
        NovoRestauranteDTO dto = RestauranteHelper.gerarNovoRestauranteDTO();

        when(gestorGateway.buscarPorId(idGestor)).thenReturn(null);

        // Act & Assert
        GestorNaoEncontradoException exception = assertThrows(
                GestorNaoEncontradoException.class,
                () -> useCase.run(dto)
        );

        assertEquals("Gestor com ID " + idGestor + " não encontrado", exception.getMessage());
        verify(gestorGateway).buscarPorId(idGestor);
        verifyNoInteractions(restauranteGateway);
    }
}

