package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoClienteDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.ClienteJaExistenteException;
import br.com.fiap.foodtech.foodtech.core.gateways.IClienteGateway;

public class CadastrarClienteUseCase {

    private final IClienteGateway clienteGateway;

    private CadastrarClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static CadastrarClienteUseCase create(IClienteGateway clienteGateway) {
        return new CadastrarClienteUseCase(clienteGateway);
    }

    public Cliente run(NovoClienteDTO novoClienteDTO) throws ClienteJaExistenteException {
        Cliente clienteExistente = clienteGateway.buscarPorEmail(novoClienteDTO.email());

        if (clienteExistente != null) {
            throw new ClienteJaExistenteException("Cliente com email " + novoClienteDTO.email() + " já existe");
        }

        Login login = new Login(novoClienteDTO.login(), novoClienteDTO.senha());

        Endereco endereco = new Endereco(
                novoClienteDTO.logradouro(),
                novoClienteDTO.numero(),
                novoClienteDTO.bairro(),
                novoClienteDTO.cidade(),
                novoClienteDTO.estado(),
                novoClienteDTO.cep()
        );

        Cliente novoCliente = new Cliente(
                novoClienteDTO.nome(),
                novoClienteDTO.email(),
                novoClienteDTO.tipoUsuario(),
                login,
                endereco
        );

        return clienteGateway.incluir(novoCliente);
    }

}
