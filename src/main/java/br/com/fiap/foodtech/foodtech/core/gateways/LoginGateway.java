package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;

public class LoginGateway implements ILoginGateway {

    private final DataSource dataSource;

    private LoginGateway(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginGateway create(DataSource dataStorageSource) {
        return new LoginGateway(dataStorageSource);
    }

    @Override
    public Login buscarPorLogin(String login) {
        var loginData = dataSource.obterLoginPorLogin(login);
        if (loginData == null) {
            return null;
        }

        return new Login(
                loginData.id(),
                loginData.login(),
                loginData.senha()
        );
    }

    @Override
    public Login atualizar(Login login) {
        dataSource.atualizarLogin(login.getId(), login.getSenha());
        return login;
    }

}
