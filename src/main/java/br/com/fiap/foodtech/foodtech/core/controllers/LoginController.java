package br.com.fiap.foodtech.foodtech.core.controllers;

import br.com.fiap.foodtech.foodtech.core.domain.usecases.AlterarSenhaUseCase;
import br.com.fiap.foodtech.foodtech.core.domain.usecases.AutenticarUsuarioUseCase;
import br.com.fiap.foodtech.foodtech.core.dtos.AlterarSenhaDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.LoginDTO;
import br.com.fiap.foodtech.foodtech.core.gateways.LoginGateway;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;

public class LoginController {

    private final DataSource dataSource;

    private LoginController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginController create(DataSource dataSource) {
        return new LoginController(dataSource);
    }

    public boolean autenticar(LoginDTO loginDTO) {
        var loginGateway = LoginGateway.create(this.dataSource);
        var useCase = AutenticarUsuarioUseCase.create(loginGateway);

        return useCase.run(loginDTO);
    }

    public void alterarSenha(AlterarSenhaDTO alterarSenhaDTO) {
        var loginGateway = LoginGateway.create(this.dataSource);
        var useCase = AlterarSenhaUseCase.create(loginGateway);

        useCase.run(alterarSenhaDTO);
    }

}
