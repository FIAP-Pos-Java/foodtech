package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.domain.helper.ItemCardapioHelper;
import br.com.fiap.foodtech.foodtech.core.exceptions.itemcardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeletarItemCardapioUseCaseTest {

    private IItemCardapioGateway itemCardapioGateway;
    private DeletarItemCardapioUseCase useCase;

    @BeforeEach
    void setUp() {
        itemCardapioGateway = mock(IItemCardapioGateway.class);
        useCase = DeletarItemCardapioUseCase.create(itemCardapioGateway);
    }

    @Test
    void deveDeletarItemCardapioComSucesso() {
        Long id = 1L;
        ItemCardapio itemExistente = ItemCardapioHelper.gerarItemCardapio();

        when(itemCardapioGateway.buscarPorId(id)).thenReturn(itemExistente);
        doNothing().when(itemCardapioGateway).deletar(id);

        useCase.run(id);

        verify(itemCardapioGateway).deletar(id);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoEncontrado() {
        Long id = 99L;
        when(itemCardapioGateway.buscarPorId(id)).thenReturn(null);

        assertThrows(ItemCardapioNaoEncontradoException.class, () -> useCase.run(id));
        verify(itemCardapioGateway, never()).deletar(anyLong());
    }
}

