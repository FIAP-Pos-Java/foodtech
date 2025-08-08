package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.AlterarSenhaDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.CredenciaisInvalidasException;
import br.com.fiap.foodtech.foodtech.core.exceptions.LoginInvalidoException;
import br.com.fiap.foodtech.foodtech.core.gateways.ILoginGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlterarSenhaUseCaseTest {

    private ILoginGateway loginGateway;
    private AlterarSenhaUseCase alterarSenhaUseCase;

    @BeforeEach
    void setUp() {
        loginGateway = mock(ILoginGateway.class);
        alterarSenhaUseCase = AlterarSenhaUseCase.create(loginGateway);
    }

    @Test
    void deveAlterarSenhaComSucesso() throws Exception {
        Login login = new Login("usuario", "senhaAntiga");
        AlterarSenhaDTO dto = new AlterarSenhaDTO("usuario", "senhaAntiga", "senhaNova");

        when(loginGateway.buscarPorLogin("usuario")).thenReturn(login);
        when(loginGateway.atualizar(any(Login.class))).thenReturn(login);

        Login resultado = alterarSenhaUseCase.run(dto);

        assertEquals("senhaNova", resultado.getSenha());
        verify(loginGateway).atualizar(login);
    }

    @Test
    void deveLancarExcecaoQuandoLoginNaoExiste() {
        AlterarSenhaDTO dto = new AlterarSenhaDTO("usuarioInexistente", "senha", "novaSenha");

        when(loginGateway.buscarPorLogin("usuarioInexistente")).thenReturn(null);

        assertThrows(LoginInvalidoException.class, () -> alterarSenhaUseCase.run(dto));
    }

    @Test
    void deveLancarExcecaoQuandoSenhaAtualIncorreta() {
        Login login = new Login("usuario", "senhaCorreta");
        AlterarSenhaDTO dto = new AlterarSenhaDTO("usuario", "senhaErrada", "novaSenha");

        when(loginGateway.buscarPorLogin("usuario")).thenReturn(login);

        assertThrows(CredenciaisInvalidasException.class, () -> alterarSenhaUseCase.run(dto));
    }

    @Test
    void deveLancarExcecaoQuandoNovaSenhaIgualASenhaAtual() {
        Login login = new Login("usuario", "senhaAtual");
        AlterarSenhaDTO dto = new AlterarSenhaDTO("usuario", "senhaAtual", "senhaAtual");

        when(loginGateway.buscarPorLogin("usuario")).thenReturn(login);

        assertThrows(CredenciaisInvalidasException.class, () -> alterarSenhaUseCase.run(dto));
    }
}

