package br.com.fiap.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.core.dto.AlterarSenhaDTO;
import br.com.fiap.foodtech.core.exceptions.CredenciaisInvalidasException;
import br.com.fiap.foodtech.core.exceptions.LoginInvalidoException;
import br.com.fiap.foodtech.core.interfaces.ILoginGateway;

public class AlterarSenhaUseCase {
    private final ILoginGateway loginGateway;

    private AlterarSenhaUseCase(ILoginGateway loginGateway) {
        this.loginGateway = loginGateway;
    }

    public static AlterarSenhaUseCase create(ILoginGateway loginGateway) {
        return new AlterarSenhaUseCase(loginGateway);
    }

    public Login run(AlterarSenhaDTO alterarSenhaDTO) throws LoginInvalidoException, CredenciaisInvalidasException {

        Login login = loginGateway.buscarPorLogin(alterarSenhaDTO.login());
        if (login == null) {
            throw new LoginInvalidoException("Usuário não encontrado");
        }


        if (!login.getSenha().equals(alterarSenhaDTO.senhaAtual())) {
            throw new CredenciaisInvalidasException("Senha atual incorreta");
        }


        if (login.getSenha().equals(alterarSenhaDTO.novaSenha())) {
            throw new CredenciaisInvalidasException("A nova senha deve ser diferente da senha atual");
        }


        login.alterarSenha(alterarSenhaDTO.novaSenha());


        return loginGateway.atualizar(login);
    }
}