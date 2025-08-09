package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.exceptions.itemcardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BuscarItemCardapioPorIdUseCaseTest {

    private IItemCardapioGateway gateway;
    private BuscarItemCardapioPorIdUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = Mockito.mock(IItemCardapioGateway.class);
        useCase = BuscarItemCardapioPorIdUseCase.create(gateway);
    }

    @Test
    void deveRetornarItemCardapioQuandoEncontrado() throws ItemCardapioNaoEncontradoException {
        Long id = 1L;
        ItemCardapio item = new ItemCardapio(id, "Pizza", "Pizza de mussarela", new BigDecimal("39.90"), true, "/pizza.jpg");

        Mockito.when(gateway.buscarPorId(id)).thenReturn(item);

        ItemCardapio resultado = useCase.run(id);

        assertNotNull(resultado);
        assertEquals("Pizza", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoEncontrado() {
        Long id = 99L;

        Mockito.when(gateway.buscarPorId(id)).thenReturn(null);

        Exception exception = assertThrows(ItemCardapioNaoEncontradoException.class, () -> useCase.run(id));
        assertEquals("Item do cardápio com ID 99 não encontrado", exception.getMessage());
    }
}

