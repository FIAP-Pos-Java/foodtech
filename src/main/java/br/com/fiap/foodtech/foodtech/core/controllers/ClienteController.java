package br.com.fiap.foodtech.foodtech.core.controllers;

import br.com.fiap.foodtech.foodtech.core.domain.usecases.*;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.core.exceptions.cliente.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.exceptions.credenciais.CredenciaisInvalidasException;
import br.com.fiap.foodtech.foodtech.core.exceptions.credenciais.LoginInvalidoException;
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

        var cliente = useCase.run(novoClienteDTO);
        return ClientePresenter.toDTO(cliente);
    }

    public ClienteDTO buscarPorId(Long id) {
        var clienteGateway = new ClienteGateway(this.dataSource);
        var useCase = new BuscarClientePorIdUseCase(clienteGateway);

        var cliente = useCase.run(id);
        return ClientePresenter.toDTO(cliente);
    }

    public ClienteDTO atualizar(Long id, ClienteDataDTO clienteDataDTO) {
        var clienteGateway = new ClienteGateway(this.dataSource);
        var useCase = AtualizarClienteUseCase.create(clienteGateway);

        var cliente = useCase.run(id, clienteDataDTO);
        return ClientePresenter.toDTO(cliente);
    }

    public void deletar(Long id) {
        var clienteGateway = new ClienteGateway(this.dataSource);
        var useCase = DeletarClienteUseCase.create(clienteGateway);

        useCase.run(id);
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
