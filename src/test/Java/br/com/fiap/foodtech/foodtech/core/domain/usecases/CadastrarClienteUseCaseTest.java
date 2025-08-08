package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoClienteDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.ClienteJaExistenteException;
import br.com.fiap.foodtech.foodtech.core.gateways.IClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastrarClienteUseCaseTest {

    private IClienteGateway clienteGateway;
    private CadastrarClienteUseCase cadastrarClienteUseCase;

    @BeforeEach
    void setUp() {
        clienteGateway = mock(IClienteGateway.class);
        cadastrarClienteUseCase = CadastrarClienteUseCase.create(clienteGateway);
    }

    @Test
    void deveCadastrarClienteComSucesso() throws Exception {
        NovoClienteDTO dto = new NovoClienteDTO(
                "Maria Oliveira",
                "maria@email.com",
                "CLIENTE",
                "maria@email.com",
                "senhaSegura",
                "Rua B",
                "456",
                "Bairro",
                "Rio de Janeiro",
                "RJ",
                "98765-432"
        );

        when(clienteGateway.buscarPorEmail(dto.email())).thenReturn(null);

        Cliente clienteEsperado = new Cliente(
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                new Login(dto.login(), dto.senha()),
                new Endereco(dto.logradouro(), dto.numero(), dto.bairro(), dto.cidade(), dto.estado(), dto.cep())
        );

        when(clienteGateway.incluir(any(Cliente.class))).thenReturn(clienteEsperado);

        Cliente resultado = cadastrarClienteUseCase.run(dto);

        assertNotNull(resultado);
        assertEquals(dto.email(), resultado.getEmail());
        verify(clienteGateway).incluir(any(Cliente.class));
    }

    @Test
    void deveLancarExcecaoQuandoClienteJaExiste() {
        NovoClienteDTO dto = new NovoClienteDTO(
                "Maria Oliveira",
                "maria@email.com",
                "CLIENTE",
                "maria@email.com",
                "senhaSegura",
                "Rua B",
                "456",
                "Bairro",
                "Rio de Janeiro",
                "RJ",
                "98765-432"
        );

        Cliente clienteExistente = new Cliente(
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                new Login(dto.login(), dto.senha()),
                new Endereco(dto.logradouro(), dto.numero(), dto.bairro(), dto.cidade(), dto.estado(), dto.cep())
        );

        when(clienteGateway.buscarPorEmail(dto.email())).thenReturn(clienteExistente);

        assertThrows(ClienteJaExistenteException.class, () -> cadastrarClienteUseCase.run(dto));
    }
}

