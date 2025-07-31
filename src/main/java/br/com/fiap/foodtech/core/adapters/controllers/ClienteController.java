package br.com.fiap.foodtech.core.adapters.controllers;

import br.com.fiap.foodtech.core.adapters.gateways.ClienteGateway;
import br.com.fiap.foodtech.core.adapters.presenters.ClientePresenter;
import br.com.fiap.foodtech.core.domain.usecases.BuscarClientePorIdUseCase;
import br.com.fiap.foodtech.core.domain.usecases.CadastrarClienteUseCase;
import br.com.fiap.foodtech.core.dto.ClienteDTO;
import br.com.fiap.foodtech.core.dto.NovoClienteDTO;
import br.com.fiap.foodtech.core.exceptions.ClienteJaExistenteException;
import br.com.fiap.foodtech.core.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;

public class ClienteController {
    private final IDataStorageSource dataStorageSource;

    private ClienteController(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    public static ClienteController create(IDataStorageSource dataStorageSource) {
        return new ClienteController(dataStorageSource);
    }

    public ClienteDTO cadastrar(NovoClienteDTO novoClienteDTO) {
        var clienteGateway = ClienteGateway.create(this.dataStorageSource);
        var useCase = CadastrarClienteUseCase.create(clienteGateway);

        try {
            var cliente = useCase.run(novoClienteDTO);
            return ClientePresenter.toDTO(cliente);
        } catch (ClienteJaExistenteException e) {
            throw e;
        }
    }

    public ClienteDTO buscarPorId(Long id) {
        var clienteGateway = ClienteGateway.create(this.dataStorageSource);
        var useCase = BuscarClientePorIdUseCase.create(clienteGateway);

        try {
            var cliente = useCase.run(id);
            return ClientePresenter.toDTO(cliente);
        } catch (ClienteNaoEncontradoException e) {
            throw e;
        }
    }
}