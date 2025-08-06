package br.com.fiap.foodtech.foodtech.core.controllers;

import br.com.fiap.foodtech.foodtech.core.domain.usecases.AlterarSenhaUseCase;
import br.com.fiap.foodtech.foodtech.core.domain.usecases.AutenticarUsuarioUseCase;
import br.com.fiap.foodtech.foodtech.core.domain.usecases.BuscarClientePorIdUseCase;
import br.com.fiap.foodtech.foodtech.core.domain.usecases.CadastrarClienteUseCase;
import br.com.fiap.foodtech.foodtech.core.dtos.AlterarSenhaDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.ClienteDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.LoginDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoClienteDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.ClienteJaExistenteException;
import br.com.fiap.foodtech.foodtech.core.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.exceptions.CredenciaisInvalidasException;
import br.com.fiap.foodtech.foodtech.core.exceptions.LoginInvalidoException;
import br.com.fiap.foodtech.foodtech.core.gateways.ClienteGateway;
import br.com.fiap.foodtech.foodtech.core.gateways.LoginGateway;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;
import br.com.fiap.foodtech.foodtech.core.presenters.ClientePresenter;

public class ClienteController {

    private final DataSource dataSource;

    private ClienteController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ClienteController create(DataSource dataSource) {
        return new ClienteController(dataSource);
    }

    public ClienteDTO cadastrar(NovoClienteDTO novoClienteDTO) {
        var clienteGateway = ClienteGateway.create(this.dataSource);
        var useCase = CadastrarClienteUseCase.create(clienteGateway);

        try {
            var cliente = useCase.run(novoClienteDTO);
            return ClientePresenter.toDTO(cliente);
        } catch (ClienteJaExistenteException e) {
            throw e;
        }
    }

    public ClienteDTO buscarPorId(Long id) {
        var clienteGateway = new ClienteGateway(this.dataSource);
        var useCase = new BuscarClientePorIdUseCase(clienteGateway);
        var cliente = useCase.run(id);
        if (cliente == null) {
            return null;
        }
        return ClientePresenter.toDTO(cliente);
    }

    public boolean autenticar(LoginDTO loginDTO) {
        var loginGateway = LoginGateway.create(this.dataSource);
        var useCase = AutenticarUsuarioUseCase.create(loginGateway);

        try {
            return useCase.run(loginDTO);
        } catch (LoginInvalidoException | CredenciaisInvalidasException e) {
            throw e;
        }
    }

    public void alterarSenha(AlterarSenhaDTO alterarSenhaDTO) {
        var loginGateway = LoginGateway.create(this.dataSource);
        var useCase = AlterarSenhaUseCase.create(loginGateway);

        try {
            useCase.run(alterarSenhaDTO);
        } catch (LoginInvalidoException | CredenciaisInvalidasException e) {
            throw e;
        }
    }

}
