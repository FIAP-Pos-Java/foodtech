package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoClienteDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;

public class ClienteGateway implements IClienteGateway {

    private final DataSource dataSource;

    public ClienteGateway(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ClienteGateway create(DataSource dataStorageSource) {
        return new ClienteGateway(dataStorageSource);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        var clienteData = dataSource.obterClientePorId(id);

        if (clienteData == null) {
            throw new ClienteNaoEncontradoException("Cliente com ID " + id + " não encontrado");
        }

        Login login = new Login(clienteData.loginData().login(), clienteData.loginData().senha());

        Endereco endereco = new Endereco(
                clienteData.enderecoData().logradouro(),
                clienteData.enderecoData().numero(),
                clienteData.enderecoData().bairro(),
                clienteData.enderecoData().cidade(),
                clienteData.enderecoData().estado(),
                clienteData.enderecoData().cep()
        );

        return new Cliente(
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
        var clienteData = dataSource.obterClientePorEmail(email);
        if (clienteData == null) {
            return null;
        }

        Login login = new Login(clienteData.loginData().login(), clienteData.loginData().senha());

        Endereco endereco = new Endereco(
                clienteData.enderecoData().logradouro(),
                clienteData.enderecoData().numero(),
                clienteData.enderecoData().bairro(),
                clienteData.enderecoData().cidade(),
                clienteData.enderecoData().estado(),
                clienteData.enderecoData().cep()
        );

        return new Cliente(
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
                cliente.getTipoUsuario(),
                cliente.getLogin().getLogin(),
                cliente.getLogin().getSenha(),
                cliente.getEndereco().getLogradouro(),
                cliente.getEndereco().getNumero(),
                cliente.getEndereco().getBairro(),
                cliente.getEndereco().getCidade(),
                cliente.getEndereco().getEstado(),
                cliente.getEndereco().getCep()
        );

        var clienteSalvo = dataSource.incluirCliente(novoClienteDTO);

        Login login = new Login(clienteSalvo.loginData().login(), clienteSalvo.loginData().senha());

        Endereco endereco = new Endereco(
                clienteSalvo.enderecoData().logradouro(),
                clienteSalvo.enderecoData().numero(),
                clienteSalvo.enderecoData().bairro(),
                clienteSalvo.enderecoData().cidade(),
                clienteSalvo.enderecoData().estado(),
                clienteSalvo.enderecoData().cep()
        );

        return new Cliente(
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
        dataSource.deletarCliente(id);
    }

}
