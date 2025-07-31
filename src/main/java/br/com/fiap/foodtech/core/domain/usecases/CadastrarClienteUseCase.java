package br.com.fiap.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.core.dto.NovoClienteDTO;
import br.com.fiap.foodtech.core.exceptions.ClienteJaExistenteException;
import br.com.fiap.foodtech.core.interfaces.IClienteGateway;

public class CadastrarClienteUseCase {
    private final IClienteGateway clienteGateway;

    private CadastrarClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static CadastrarClienteUseCase create(IClienteGateway clienteGateway) {
        return new CadastrarClienteUseCase(clienteGateway);
    }

    public Cliente run(NovoClienteDTO novoClienteDTO) throws ClienteJaExistenteException {
        try {
            Cliente clienteExistente = clienteGateway.buscarPorEmail(novoClienteDTO.email());
            if (clienteExistente != null) {
                throw new ClienteJaExistenteException("Cliente com email " + novoClienteDTO.email() + " já existe");
            }
        } catch (Exception e) {
        }

        Login login = Login.create(novoClienteDTO.login(), novoClienteDTO.senha());
        Endereco endereco = Endereco.create(
                novoClienteDTO.logradouro(),
                novoClienteDTO.numero(),
                novoClienteDTO.bairro(),
                novoClienteDTO.cidade(),
                novoClienteDTO.estado(),
                novoClienteDTO.cep()
        );

        Cliente novoCliente = Cliente.create(
                novoClienteDTO.nome(),
                novoClienteDTO.email(),
                login,
                endereco
        );

        return clienteGateway.incluir(novoCliente);
    }
}