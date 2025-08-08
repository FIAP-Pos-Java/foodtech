package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.core.gateways.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastrarItemCardapioUseCaseTest {

    private IItemCardapioGateway itemCardapioGateway;
    private CadastrarItemCardapioUseCase useCase;

    @BeforeEach
    void setUp() {
        itemCardapioGateway = mock(IItemCardapioGateway.class);
        useCase = CadastrarItemCardapioUseCase.create(itemCardapioGateway);
    }

    @Test
    void deveCadastrarItemCardapioComSucesso() {
        // Arrange
        NovoItemCardapioDTO dto = new NovoItemCardapioDTO(
                "Pizza Margherita",
                "Pizza clássica com molho de tomate e manjericão",
                new BigDecimal("39.90"),
                true,
                "/imagens/pizza.jpg"
        );

        ItemCardapio itemEsperado = new ItemCardapio(
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.disponibilidadeRestaurante(),
                dto.caminhoFoto()
        );

        when(itemCardapioGateway.incluir(any(ItemCardapio.class))).thenReturn(itemEsperado);

        // Act
        ItemCardapio resultado = useCase.run(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(dto.nome(), resultado.getNome());
        assertEquals(dto.descricao(), resultado.getDescricao());
        assertEquals(dto.preco(), resultado.getPreco());
        assertEquals(dto.disponibilidadeRestaurante(), resultado.getDisponibilidadeRestaurante());
        assertEquals(dto.caminhoFoto(), resultado.getCaminhoFoto());

        verify(itemCardapioGateway).incluir(any(ItemCardapio.class));
    }
}
