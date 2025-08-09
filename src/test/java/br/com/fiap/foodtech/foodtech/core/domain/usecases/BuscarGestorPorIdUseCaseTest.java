package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IGestorGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarGestorPorIdUseCaseTest {

    private IGestorGateway gestorGateway;
    private BuscarGestorPorIdUseCase useCase;

    @BeforeEach
    void setUp() {
        gestorGateway = mock(IGestorGateway.class);
        useCase = BuscarGestorPorIdUseCase.create(gestorGateway);
    }

    @Test
    void deveRetornarGestorQuandoEncontrado() throws GestorNaoEncontradoException {
        // Arrange
        Long id = 1L;
        Endereco enderecoGestor = new Endereco(1L, "Rua do Gestor",
                "456",
                "Bairro do Gestor",
                "Cidade do Gestor",
                "Estado do Gestor",
                "12345-678");
        Gestor gestor = new Gestor(id,
                "Maria",
                "maria@email.com",
                "GESTOR",
                enderecoGestor,
                new Login("maria@email.com","senha123"));
        when(gestorGateway.buscarPorId(id)).thenReturn(gestor);

        // Act
        Gestor resultado = useCase.run(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(gestor.getId(), resultado.getId());
        assertEquals(gestor.getNome(), resultado.getNome());
        assertEquals(gestor.getEmail(), resultado.getEmail());

        verify(gestorGateway).buscarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoGestorNaoEncontrado() {
        // Arrange
        Long id = 99L;
        when(gestorGateway.buscarPorId(id)).thenReturn(null);

        // Act & Assert
        GestorNaoEncontradoException exception = assertThrows(
                GestorNaoEncontradoException.class,
                () -> useCase.run(id)
        );

        assertEquals("Gestor com ID " + id + " não encontrado", exception.getMessage());
        verify(gestorGateway).buscarPorId(id);
    }
}

