package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.dtos.Pagina;
import br.com.fiap.foodtech.foodtech.core.dtos.Paginacao;
import br.com.fiap.foodtech.foodtech.core.exceptions.cliente.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IClienteGateway;

public class ListarClientesUseCase {

    private final IClienteGateway clienteGateway;

    public ListarClientesUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static ListarClientesUseCase create(IClienteGateway clienteGateway) {
        return new ListarClientesUseCase(clienteGateway);
    }

    public Pagina<Cliente> run(Paginacao paginacao) throws ClienteNaoEncontradoException {
        return clienteGateway.buscarTodos(paginacao);
    }

}
