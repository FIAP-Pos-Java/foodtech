package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.ClienteDataDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.cliente.ClienteJaExistenteException;
import br.com.fiap.foodtech.foodtech.core.exceptions.cliente.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IClienteGateway;

public class AtualizarClienteUseCase {

    private final IClienteGateway clienteGateway;

    private AtualizarClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static AtualizarClienteUseCase create(IClienteGateway clienteGateway) {
        return new AtualizarClienteUseCase(clienteGateway);
    }

    public Cliente run(Long id, ClienteDataDTO clienteDataDTO) throws ClienteJaExistenteException {
        Cliente clienteExistente = clienteGateway.buscarPorId(id);

        if (clienteExistente == null) {
            throw new ClienteNaoEncontradoException("Cliente com ID " + id + " não encontrado");
        }

        Login login = new Login(
                clienteExistente.getLogin().getId(),
                clienteDataDTO.login().login()
        );

        Endereco endereco = new Endereco(
                clienteExistente.getEndereco().getId(),
                clienteDataDTO.endereco().logradouro(),
                clienteDataDTO.endereco().numero(),
                clienteDataDTO.endereco().bairro(),
                clienteDataDTO.endereco().cidade(),
                clienteDataDTO.endereco().estado(),
                clienteDataDTO.endereco().cep()
        );

        Cliente clienteAtualizado = new Cliente(
                clienteExistente.getId(),
                clienteDataDTO.nome(),
                clienteDataDTO.email(),
                clienteDataDTO.tipoUsuario(),
                login,
                endereco
        );

        return clienteGateway.atualizar(clienteAtualizado);
    }
}
