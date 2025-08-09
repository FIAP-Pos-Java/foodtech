package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.LoginDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.credenciais.CredenciaisInvalidasException;
import br.com.fiap.foodtech.foodtech.core.exceptions.credenciais.LoginInvalidoException;
import br.com.fiap.foodtech.foodtech.core.gateways.ILoginGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutenticarUsuarioUseCaseTest {

    private ILoginGateway loginGateway;
    private AutenticarUsuarioUseCase useCase;

    @BeforeEach
    void setUp() {
        loginGateway = mock(ILoginGateway.class);
        useCase = AutenticarUsuarioUseCase.create(loginGateway);
    }

    @Test
    void deveAutenticarUsuarioComCredenciaisValidas() throws LoginInvalidoException, CredenciaisInvalidasException {
        // Arrange
        Long id = 1L;
        String login = "usuario@email.com";
        String senha = "senha123";
        LoginDTO dto = new LoginDTO(id, login, senha);
        Login loginEntity = new Login(login, senha);

        when(loginGateway.buscarPorLogin(login)).thenReturn(loginEntity);

        // Act
        boolean resultado = useCase.run(dto);

        // Assert
        assertTrue(resultado);
        verify(loginGateway).buscarPorLogin(login);
    }

    @Test
    void deveLancarExcecaoQuandoLoginNaoEncontrado() {
        // Arrange
        Long id = 1L;
        String login = "naoexiste@email.com";
        LoginDTO dto = new LoginDTO(id, login, "qualquerSenha");

        when(loginGateway.buscarPorLogin(login)).thenReturn(null);

        // Act & Assert
        LoginInvalidoException exception = assertThrows(
                LoginInvalidoException.class,
                () -> useCase.run(dto)
        );

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(loginGateway).buscarPorLogin(login);
    }

    @Test
    void deveLancarExcecaoQuandoSenhaIncorreta() {
        // Arrange
        Long id = 1L;
        String login = "usuario@email.com";
        LoginDTO dto = new LoginDTO(id, login, "senhaErrada");
        Login loginEntity = new Login(login, "senhaCorreta");

        when(loginGateway.buscarPorLogin(login)).thenReturn(loginEntity);

        // Act & Assert
        CredenciaisInvalidasException exception = assertThrows(
                CredenciaisInvalidasException.class,
                () -> useCase.run(dto)
        );

        assertEquals("Senha incorreta", exception.getMessage());
        verify(loginGateway).buscarPorLogin(login);
    }
}

