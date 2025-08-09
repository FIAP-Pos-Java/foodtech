
package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.dtos.ItemCardapioDataDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.itemcardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    class AtualizarItemCardapioUseCaseTest {

        private IItemCardapioGateway itemCardapioGateway;
        private AtualizarItemCardapioUseCase useCase;

        @BeforeEach
        void setUp() {
            itemCardapioGateway = mock(IItemCardapioGateway.class);
            useCase = AtualizarItemCardapioUseCase.create(itemCardapioGateway);
        }

        @Test
        void deveAtualizarItemCardapioComSucesso() {
            Long id = 1L;
            ItemCardapio existente = new ItemCardapio(id, "Pizza",
                    "Pizza de calabresa",
                    new BigDecimal("39.90"),
                    true,
                    "/imagens/pizza.jpg"
            );
            ItemCardapioDataDTO dto = new ItemCardapioDataDTO(1L,
                    "Pizza Calabresa",
                    "Com calabresa",
                    new BigDecimal(39.90),
                    true,
                    "nova_foto.jpg");
            ItemCardapio atualizado = new ItemCardapio(id, dto.nome(), dto.descricao(), dto.preco(), dto.disponibilidadeRestaurante(), dto.caminhoFoto());

            when(itemCardapioGateway.buscarPorId(id)).thenReturn(existente);
            when(itemCardapioGateway.atualizar(any(ItemCardapio.class))).thenReturn(atualizado);

            ItemCardapio resultado = useCase.run(id, dto);

            assertEquals(dto.nome(), resultado.getNome());
            assertEquals(dto.descricao(), resultado.getDescricao());
            assertEquals(dto.preco(), resultado.getPreco());
            assertEquals(dto.disponibilidadeRestaurante(), resultado.getDisponibilidadeRestaurante());
            assertEquals(dto.caminhoFoto(), resultado.getCaminhoFoto());
        }

        @Test
        void deveLancarExcecaoQuandoItemNaoEncontrado() {
            Long id = 99L;
            ItemCardapioDataDTO dto = new ItemCardapioDataDTO(1L,
                    "Pizza Calabresa",
                    "Com calabresa",
                    new BigDecimal(39.90),
                    true,
                    "nova_foto.jpg");

            when(itemCardapioGateway.buscarPorId(id)).thenReturn(null);

            assertThrows(ItemCardapioNaoEncontradoException.class, () -> useCase.run(id, dto));
        }
    }
