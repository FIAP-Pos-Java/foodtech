package br.com.fiap.foodtech.core.interfaces;

import br.com.fiap.foodtech.core.domain.entities.Login;

public interface ILoginGateway {
    Login buscarPorLogin(String login);
    Login atualizar(Login login);
}