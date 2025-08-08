package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.exceptions.cliente.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IClienteGateway;

public class BuscarClientePorIdUseCase {

    private final IClienteGateway clienteGateway;

    public BuscarClientePorIdUseCase(IClienteGateway clienteGateway) {
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
