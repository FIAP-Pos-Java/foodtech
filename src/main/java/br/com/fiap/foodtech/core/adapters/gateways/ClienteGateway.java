package br.com.fiap.foodtech.core.adapters.gateways;

import br.com.fiap.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.core.dto.NovoClienteDTO;
import br.com.fiap.foodtech.core.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IClienteGateway;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;

public class ClienteGateway implements IClienteGateway {
    private final IDataStorageSource dataStorageSource;

    private ClienteGateway(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    public static ClienteGateway create(IDataStorageSource dataStorageSource) {
        return new ClienteGateway(dataStorageSource);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        var clienteData = dataStorageSource.obterClientePorId(id);
        if (clienteData == null) {
            throw new ClienteNaoEncontradoException("Cliente com ID " + id + " não encontrado");
        }

        Login login = Login.create(clienteData.loginData().login(), clienteData.loginData().senha());
        Endereco endereco = Endereco.create(
                clienteData.enderecoData().logradouro(),
                clienteData.enderecoData().numero(),
                clienteData.enderecoData().bairro(),
                clienteData.enderecoData().cidade(),
                clienteData.enderecoData().estado(),
                clienteData.enderecoData().cep()
        );

        return Cliente.create(
                clienteData.id(),
                clienteData.nome(),
                clienteData.email(),
                clienteData.tipoUsuario(),
                login,
                endereco
        );
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        var clienteData = dataStorageSource.obterClientePorEmail(email);
        if (clienteData == null) {
            return null;
        }

        Login login = Login.create(clienteData.loginData().login(), clienteData.loginData().senha());
        Endereco endereco = Endereco.create(
                clienteData.enderecoData().logradouro(),
                clienteData.enderecoData().numero(),
                clienteData.enderecoData().bairro(),
                clienteData.enderecoData().cidade(),
                clienteData.enderecoData().estado(),
                clienteData.enderecoData().cep()
        );

        return Cliente.create(
                clienteData.id(),
                clienteData.nome(),
                clienteData.email(),
                clienteData.tipoUsuario(),
                login,
                endereco
        );
    }

    @Override
    public Cliente incluir(Cliente cliente) {
        NovoClienteDTO novoClienteDTO = new NovoClienteDTO(
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getLogin().getLogin(),
                cliente.getLogin().getSenha(),
                cliente.getEndereco().getLogradouro(),
                cliente.getEndereco().getNumero(),
                cliente.getEndereco().getBairro(),
                cliente.getEndereco().getCidade(),
                cliente.getEndereco().getEstado(),
                cliente.getEndereco().getCep()
        );

        var clienteSalvo = dataStorageSource.incluirCliente(novoClienteDTO);

        Login login = Login.create(clienteSalvo.loginData().login(), clienteSalvo.loginData().senha());
        Endereco endereco = Endereco.create(
                clienteSalvo.enderecoData().logradouro(),
                clienteSalvo.enderecoData().numero(),
                clienteSalvo.enderecoData().bairro(),
                clienteSalvo.enderecoData().cidade(),
                clienteSalvo.enderecoData().estado(),
                clienteSalvo.enderecoData().cep()
        );

        return Cliente.create(
                clienteSalvo.id(),
                clienteSalvo.nome(),
                clienteSalvo.email(),
                clienteSalvo.tipoUsuario(),
                login,
                endereco
        );
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        throw new UnsupportedOperationException("Não implementado ainda");
    }

    @Override
    public void deletar(Long id) {
        dataStorageSource.deletarCliente(id);
    }
}