package br.com.fiap.foodtech.core.adapters.gateways;

import br.com.fiap.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;
import br.com.fiap.foodtech.core.interfaces.ILoginGateway;

public class LoginGateway implements ILoginGateway {
    private final IDataStorageSource dataStorageSource;

    private LoginGateway(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    public static LoginGateway create(IDataStorageSource dataStorageSource) {
        return new LoginGateway(dataStorageSource);
    }

    @Override
    public Login buscarPorLogin(String login) {
        var loginData = dataStorageSource.obterLoginPorLogin(login);
        if (loginData == null) {
            return null;
        }

        return Login.create(
                loginData.id(),
                loginData.login(),
                loginData.senha()
        );
    }

    @Override
    public Login atualizar(Login login) {
        dataStorageSource.atualizarLogin(login.getId(), login.getSenha());
        return login;
    }
}