package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.domain.helper.RestauranteHelper;
import br.com.fiap.foodtech.foodtech.core.dtos.Pagina;
import br.com.fiap.foodtech.foodtech.core.dtos.Paginacao;
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

            Paginacao paginacao = new Paginacao(page, size);

            List<Restaurante> restaurantesEsperados = List.of(
                    RestauranteHelper.gerarRestaurante(),
                    RestauranteHelper.gerarRestaurante()
            );

            Pagina<Restaurante> pagina = new Pagina<>(restaurantesEsperados, 2);

            when(restauranteGateway.buscarTodos(paginacao)).thenReturn(pagina);

            // Act
            Pagina<Restaurante> resultado = useCase.run(paginacao);

            // Assert
            assertNotNull(resultado);
            assertEquals(2, resultado.content().size());
            assertEquals(restaurantesEsperados.get(0).getNome(), resultado.content().get(0).getNome());
            assertEquals(restaurantesEsperados.get(1).getNome(), resultado.content().get(1).getNome());

            verify(restauranteGateway).buscarTodos(paginacao);
        }
}
