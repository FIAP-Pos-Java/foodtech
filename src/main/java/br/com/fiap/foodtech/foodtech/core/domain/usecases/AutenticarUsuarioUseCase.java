package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.LoginDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.CredenciaisInvalidasException;
import br.com.fiap.foodtech.foodtech.core.exceptions.LoginInvalidoException;
import br.com.fiap.foodtech.foodtech.core.gateways.ILoginGateway;

public class AutenticarUsuarioUseCase {

    private final ILoginGateway loginGateway;

    private AutenticarUsuarioUseCase(ILoginGateway loginGateway) {
        this.loginGateway = loginGateway;
    }

    public static AutenticarUsuarioUseCase create(ILoginGateway loginGateway) {
        return new AutenticarUsuarioUseCase(loginGateway);
    }

    public boolean run(LoginDTO loginDTO) throws LoginInvalidoException, CredenciaisInvalidasException {
        Login login = loginGateway.buscarPorLogin(loginDTO.login());

        if (login == null) {
            throw new LoginInvalidoException("Usuário não encontrado");
        }

        if (!login.getSenha().equals(loginDTO.senha())) {
            throw new CredenciaisInvalidasException("Senha incorreta");
        }

        return true;
    }

}
