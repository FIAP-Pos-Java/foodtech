package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.exceptions.cliente.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IClienteGateway;

public class DeletarClienteUseCase {

    private final IClienteGateway clienteGateway;

    private DeletarClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static DeletarClienteUseCase create(IClienteGateway clienteGateway) {
        return new DeletarClienteUseCase(clienteGateway);
    }

    public void run(Long id) {
        Cliente clienteExistente = clienteGateway.buscarPorId(id);

        if (clienteExistente == null) {
            throw new ClienteNaoEncontradoException("Cliente com ID " + id + " não encontrado");
        }
        clienteGateway.deletar(id);
    }
}
