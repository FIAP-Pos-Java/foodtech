package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IGestorGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtualizarGestorUseCaseTest {

    private IGestorGateway gestorGateway;
    private AtualizarGestorUseCase useCase;

    @BeforeEach
    void setUp() {
        gestorGateway = mock(IGestorGateway.class);
        useCase = AtualizarGestorUseCase.create(gestorGateway);
    }

    @Test
    void deveAtualizarGestorComSucesso() {
        Long id = 1L;

        Gestor gestorExistente = new Gestor(
                id,
                "Carlos",
                "carlos@email.com",
                "GESTOR",
                new Endereco(10L, "Rua A", "100", "Centro", "São Paulo", "SP", "01000-000"),
                new Login(20L, "carlos123")
        );

        GestorDataDTO dto = new GestorDataDTO(1L,
                "Maria Oliveira",
                "maria@email.com",
                "GESTOR",
                new LoginDataDTO(1L, "senhaSegura"),
                new EnderecoDataDTO(1L, "Rua B", "456", "Bairro", "Rio de Janeiro", "RJ", "98765-432"));


        Gestor gestorAtualizado = new Gestor(
                id,
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                new Endereco(gestorExistente.getEndereco().getId(),
                        dto.endereco().logradouro(),
                        dto.endereco().numero(),
                        dto.endereco().bairro(),
                        dto.endereco().cidade(),
                        dto.endereco().estado(),
                        dto.endereco().cep()),
                new Login(gestorExistente.getLogin().getId(), dto.login().login())
        );

        when(gestorGateway.buscarPorId(id)).thenReturn(gestorExistente);
        when(gestorGateway.atualizar(any(Gestor.class))).thenReturn(gestorAtualizado);

        Gestor resultado = useCase.run(id, dto);

        assertEquals(dto.nome(), resultado.getNome());
        assertEquals(dto.email(), resultado.getEmail());
        assertEquals(dto.tipoUsuario(), resultado.getTipoUsuario());
        assertEquals(dto.login().login(), resultado.getLogin().getLogin());
        assertEquals(dto.endereco().logradouro(), resultado.getEndereco().getLogradouro());
    }

    @Test
    void deveLancarExcecaoQuandoGestorNaoEncontrado() {
        Long id = 99L;
        GestorDataDTO dto = new GestorDataDTO(1L,
                "Maria Oliveira",
                "maria@email.com",
                "GESTOR",
                new LoginDataDTO(1L, "senhaSegura"),
                new EnderecoDataDTO(1L, "Rua B", "456", "Bairro", "Rio de Janeiro", "RJ", "98765-432"));


        when(gestorGateway.buscarPorId(id)).thenReturn(null);

        assertThrows(GestorNaoEncontradoException.class, () -> useCase.run(id, dto));
    }
}
