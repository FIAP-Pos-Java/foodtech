package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.core.exceptions.cliente.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarClienteUseCaseTest {

    private IClienteGateway clienteGateway;
    private AtualizarClienteUseCase useCase;

    @BeforeEach
    void setUp() {
        clienteGateway = mock(IClienteGateway.class);
        useCase = AtualizarClienteUseCase.create(clienteGateway);
    }

    @Test
    void deveAtualizarClienteComSucesso() {
        Long id = 1L;

        Cliente clienteExistente = new Cliente(
                id,
                "João",
                "joao@email.com",
                "CLIENTE",
                new Login(10L, "joao123"),
                new Endereco(20L, "Rua A", "100", "Centro", "São Paulo", "SP", "01000-000")
        );

        ClienteDataDTO dto = new ClienteDataDTO(1L,
                "Maria Oliveira",
                "maria@email.com",
                "CLIENTE",
                new LoginDataDTO(1L, "senhaSegura"),
                new EnderecoDataDTO(1L, "Rua B", "456", "Bairro", "Rio de Janeiro", "RJ", "98765-432"));

        Cliente clienteAtualizado = new Cliente(
                id,
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                new Login(clienteExistente.getLogin().getId(), dto.login().login()),
                new Endereco(clienteExistente.getEndereco().getId(),
                        dto.endereco().logradouro(),
                        dto.endereco().numero(),
                        dto.endereco().bairro(),
                        dto.endereco().cidade(),
                        dto.endereco().estado(),
                        dto.endereco().cep())
        );

        when(clienteGateway.buscarPorId(id)).thenReturn(clienteExistente);
        when(clienteGateway.atualizar(any(Cliente.class))).thenReturn(clienteAtualizado);

        Cliente resultado = useCase.run(id, dto);

        assertEquals(dto.nome(), resultado.getNome());
        assertEquals(dto.email(), resultado.getEmail());
        assertEquals(dto.tipoUsuario(), resultado.getTipoUsuario());
        assertEquals(dto.login().login(), resultado.getLogin().getLogin());
        assertEquals(dto.endereco().logradouro(), resultado.getEndereco().getLogradouro());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        Long id = 99L;
        ClienteDataDTO dto = new ClienteDataDTO(
                1L,
                "Maria Oliveira",
                "maria@email.com",
                "CLIENTE",
                new LoginDataDTO(1L, "senhaSegura"),
                new EnderecoDataDTO(1L, "Rua B", "456", "Bairro", "Rio de Janeiro", "RJ", "98765-432"));

        when(clienteGateway.buscarPorId(id)).thenReturn(null);

        assertThrows(ClienteNaoEncontradoException.class, () -> useCase.run(id, dto));
    }
}

