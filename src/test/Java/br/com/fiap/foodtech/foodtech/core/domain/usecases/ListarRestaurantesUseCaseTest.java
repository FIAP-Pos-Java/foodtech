package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.domain.helper.RestauranteHelper;
import br.com.fiap.foodtech.foodtech.core.gateways.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListarRestaurantesUseCaseTest {


        private IRestauranteGateway restauranteGateway;
        private ListarRestaurantesUseCase useCase;

        @BeforeEach
        void setUp() {
            restauranteGateway = mock(IRestauranteGateway.class);
            useCase = ListarRestaurantesUseCase.create(restauranteGateway);
        }

        @Test
        void deveRetornarListaDeRestaurantes() {
            // Arrange
            int page = 0;
            int size = 10;
            List<Restaurante> restaurantesEsperados = List.of(
                    RestauranteHelper.gerarRestaurante(),
                    RestauranteHelper.gerarRestaurante()
            );

            when(restauranteGateway.buscarTodos(page, size)).thenReturn(restaurantesEsperados);

            // Act
            List<Restaurante> resultado = useCase.run(page, size);

            // Assert
            assertNotNull(resultado);
            assertEquals(2, resultado.size());
            assertEquals(restaurantesEsperados.get(0).getNome(), resultado.get(0).getNome());
            assertEquals(restaurantesEsperados.get(1).getNome(), resultado.get(1).getNome());

            verify(restauranteGateway).buscarTodos(page, size);
        }
}
