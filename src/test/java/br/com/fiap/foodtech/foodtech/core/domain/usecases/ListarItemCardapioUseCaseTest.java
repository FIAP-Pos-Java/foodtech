package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;

import br.com.fiap.foodtech.foodtech.core.domain.helper.ItemCardapioHelper;
import br.com.fiap.foodtech.foodtech.core.dtos.Pagina;
import br.com.fiap.foodtech.foodtech.core.dtos.Paginacao;
import br.com.fiap.foodtech.foodtech.core.gateways.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ListarItemCardapioUseCaseTest {


        private IItemCardapioGateway itemCardapioGateway;
        private ListarItensCardapioUseCase useCase;

        @BeforeEach
        void setUp() {
            itemCardapioGateway = mock(IItemCardapioGateway.class);
            useCase = ListarItensCardapioUseCase.create(itemCardapioGateway);
        }

        @Test
        void deveRetornarListaDeItensCardapio() {
            // Arrange
            int page = 0;
            int size = 10;

            Paginacao paginacao = new Paginacao(page, size);

            List<ItemCardapio> itensCardapioEsperados = List.of(
                    ItemCardapioHelper.gerarItemCardapio(),
                    ItemCardapioHelper.gerarItemCardapio()
            );

            Pagina<ItemCardapio> pagina = new Pagina<>(itensCardapioEsperados, 2);

            when(itemCardapioGateway.buscarTodos(paginacao)).thenReturn(pagina);

            // Act
            Pagina<ItemCardapio> itemCardapio = useCase.run(paginacao);

            // Assert
            assertNotNull(itemCardapio);
            assertEquals(2, itemCardapio.content().size());
            assertEquals(itensCardapioEsperados.get(0).getNome(), itemCardapio.content().get(0).getNome());
            assertEquals(itensCardapioEsperados.get(1).getNome(), itemCardapio.content().get(1).getNome());

            verify(itemCardapioGateway).buscarTodos(paginacao);
        }
}
