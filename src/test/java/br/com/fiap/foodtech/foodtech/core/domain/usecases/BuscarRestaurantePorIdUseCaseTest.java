package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.*;
import br.com.fiap.foodtech.foodtech.core.domain.helper.RestauranteHelper;
import br.com.fiap.foodtech.foodtech.core.exceptions.restaurante.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class BuscarRestaurantePorIdUseCaseTest {

    private RestauranteGateway gateway;
    private BuscarRestaurantePorIdUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(RestauranteGateway.class);
        useCase = BuscarRestaurantePorIdUseCase.create(gateway);
    }

    @Test
    void deveRetornarRestauranteQuandoEncontrado() throws RestauranteNaoEncontradoException {
        // Arrange
        Long id = 1L;
        Restaurante restaurante = RestauranteHelper.gerarRestaurante();
        when(gateway.buscarPorId(id)).thenReturn(restaurante);

        // Act
        Restaurante resultado = useCase.run(id);

        // Assert
        assertNotNull(resultado);
        assertEquals("Restaurante Exemplo", resultado.getNome());
        verify(gateway).buscarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoEncontrado() {
        Long id = 99L;

        when(gateway.buscarPorId(id)).thenReturn(null);

        Exception exception = assertThrows(RestauranteNaoEncontradoException.class, () -> useCase.run(id));
        assertEquals("Restaurante com ID 99 não encontrado", exception.getMessage());
    }
}

