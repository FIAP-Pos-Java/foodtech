package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;

public interface ILoginGateway {

    Login buscarPorLogin(String login);

    Login atualizar(Login login);

}
