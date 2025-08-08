package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarClientePorIdUseCaseTest {

    private IClienteGateway clienteGateway;
    private BuscarClientePorIdUseCase useCase;

    @BeforeEach
    void setUp() {
        clienteGateway = mock(IClienteGateway.class);
        useCase = BuscarClientePorIdUseCase.create(clienteGateway);
    }

    @Test
    void deveRetornarClienteQuandoEncontrado() throws ClienteNaoEncontradoException {
        // Arrange
        Long id = 1L;
        Endereco enderecoCliente = new Endereco(1L, "Rua do Cliente",
                "456",
                "Bairro do Cliente",
                "Cidade do Cliente",
                "Estado do Cliente",
                "12345-678");
        Cliente cliente = new Cliente(id,
                "Maria",
                "maria@email.com",
                "CLIENTE",
        new Login("maria@email.com","senha123"), enderecoCliente);
        when(clienteGateway.buscarPorId(id)).thenReturn(cliente);

        // Act
        Cliente resultado = useCase.run(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(cliente.getId(), resultado.getId());
        assertEquals(cliente.getNome(), resultado.getNome());
        assertEquals(cliente.getEmail(), resultado.getEmail());

        verify(clienteGateway).buscarPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        // Arrange
        Long id = 99L;
        when(clienteGateway.buscarPorId(id)).thenReturn(null);

        // Act & Assert
        ClienteNaoEncontradoException exception = assertThrows(
                ClienteNaoEncontradoException.class,
                () -> useCase.run(id)
        );

        assertEquals("Cliente com ID " + id + " não encontrado", exception.getMessage());
        verify(clienteGateway).buscarPorId(id);
    }
}

