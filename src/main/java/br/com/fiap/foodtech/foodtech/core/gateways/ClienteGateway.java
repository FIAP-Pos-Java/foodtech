package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
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
           return null;
        }
        return dtoToCliente(clienteData);
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        var clienteData = dataSource.obterClientePorEmail(email);

        if (clienteData == null) {
            return null;
        }
        return dtoToCliente(clienteData);
    }

    @Override
    public Cliente incluir(Cliente cliente) {
        NovoClienteDTO novoCliente = new NovoClienteDTO(
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTipoUsuario(),
                new NovoLoginDTO(
                        cliente.getLogin().getLogin(),
                        cliente.getLogin().getSenha()
                ),
                new NovoEnderecoDTO(
                        cliente.getEndereco().getLogradouro(),
                        cliente.getEndereco().getNumero(),
                        cliente.getEndereco().getBairro(),
                        cliente.getEndereco().getCidade(),
                        cliente.getEndereco().getEstado(),
                        cliente.getEndereco().getCep()
                )
        );
        var clienteSalvo = dataSource.incluirCliente(novoCliente);
        return dtoToCliente(clienteSalvo);
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        ClienteDataDTO clienteDataDTO = new ClienteDataDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTipoUsuario(),
                new LoginDataDTO(
                        cliente.getLogin().getId(),
                        cliente.getLogin().getLogin()
                ),
                new EnderecoDataDTO(
                        cliente.getEndereco().getId(),
                        cliente.getEndereco().getLogradouro(),
                        cliente.getEndereco().getNumero(),
                        cliente.getEndereco().getBairro(),
                        cliente.getEndereco().getCidade(),
                        cliente.getEndereco().getEstado(),
                        cliente.getEndereco().getCep()
                )
        );
        var clienteAtualizado = dataSource.atualizarCliente(clienteDataDTO);
        return dtoToCliente(clienteAtualizado);
    }

    @Override
    public void deletar(Long id) {
        dataSource.deletarCliente(id);
    }

    private Cliente dtoToCliente(ClienteDataDTO clienteSalvo) {
        Login login = new Login(clienteSalvo.loginData().id(), clienteSalvo.loginData().login());

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
}
