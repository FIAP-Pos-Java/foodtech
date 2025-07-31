package br.com.fiap.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.core.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IClienteGateway;

public class BuscarClientePorIdUseCase {
    private final IClienteGateway clienteGateway;

    private BuscarClientePorIdUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static BuscarClientePorIdUseCase create(IClienteGateway clienteGateway) {
        return new BuscarClientePorIdUseCase(clienteGateway);
    }

    public Cliente run(Long id) throws ClienteNaoEncontradoException {
        Cliente cliente = clienteGateway.buscarPorId(id);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente com ID " + id + " não encontrado");
        }
        return cliente;
    }
}
