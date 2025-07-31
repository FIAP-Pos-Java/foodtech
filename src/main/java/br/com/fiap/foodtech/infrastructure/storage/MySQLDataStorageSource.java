package br.com.fiap.foodtech.infrastructure.storage;

import br.com.fiap.foodtech.core.dto.*;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;
import br.com.fiap.foodtech.infrastructure.persistence.entities.*;
import br.com.fiap.foodtech.infrastructure.persistence.repositories.*;
import org.springframework.stereotype.Component;

@Component
public class MySQLDataStorageSource implements IDataStorageSource {

    private final ClienteJpaRepository clienteRepository;
    private final GestorJpaRepository gestorRepository;
    private final LoginJpaRepository loginRepository;

    public MySQLDataStorageSource(
            ClienteJpaRepository clienteRepository,
            GestorJpaRepository gestorRepository,
            LoginJpaRepository loginRepository) {
        this.clienteRepository = clienteRepository;
        this.gestorRepository = gestorRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    public ClienteDataDTO obterClientePorId(Long id) {
        var clienteEntity = clienteRepository.findByIdWithDetails(id)
                .orElse(null);

        if (clienteEntity == null) {
            return null;
        }

        return mapToClienteDataDTO(clienteEntity);
    }

    @Override
    public ClienteDataDTO obterClientePorEmail(String email) {
        var clienteEntity = clienteRepository.findByEmailWithDetails(email)
                .orElse(null);

        if (clienteEntity == null) {
            return null;
        }

        return mapToClienteDataDTO(clienteEntity);
    }

    @Override
    public ClienteDataDTO incluirCliente(NovoClienteDTO novoCliente) {
        // Converte o DTO do domínio para entidade JPA
        var clienteEntity = new ClienteEntity();
        clienteEntity.setNome(novoCliente.nome());
        clienteEntity.setEmail(novoCliente.email());
        clienteEntity.setTipoUsuario("CLIENTE");

        // Cria entidade de Login
        var loginEntity = new LoginEntity();
        loginEntity.setLogin(novoCliente.login());
        loginEntity.setSenha(novoCliente.senha());
        clienteEntity.setLogin(loginEntity);

        // Cria entidade de Endereço
        var enderecoEntity = new EnderecoEntity();
        enderecoEntity.setLogradouro(novoCliente.logradouro());
        enderecoEntity.setNumero(novoCliente.numero());
        enderecoEntity.setBairro(novoCliente.bairro());
        enderecoEntity.setCidade(novoCliente.cidade());
        enderecoEntity.setEstado(novoCliente.estado());
        enderecoEntity.setCep(novoCliente.cep());
        clienteEntity.setEndereco(enderecoEntity);

        // Salva no banco de dados (cascade salva login e endereco automaticamente)
        var clienteSalvo = clienteRepository.save(clienteEntity);

        return mapToClienteDataDTO(clienteSalvo);
    }

    @Override
    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // Métodos auxiliares para conversão Entity -> DTO
    private ClienteDataDTO mapToClienteDataDTO(ClienteEntity entity) {
        return new ClienteDataDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTipoUsuario(),
                mapToLoginDataDTO(entity.getLogin()),
                mapToEnderecoDataDTO(entity.getEndereco())
        );
    }

    private LoginDataDTO mapToLoginDataDTO(LoginEntity entity) {
        if (entity == null) return null;
        return new LoginDataDTO(
                entity.getId(),
                entity.getLogin(),
                entity.getSenha()
        );
    }

    private EnderecoDataDTO mapToEnderecoDataDTO(EnderecoEntity entity) {
        if (entity == null) return null;
        return new EnderecoDataDTO(
                entity.getId(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getCep()
        );
    }

    // Implementações para Gestor e Login (simplificadas)
    @Override
    public GestorDataDTO obterGestorPorId(Long id) {
        // Implementação similar ao cliente
        throw new UnsupportedOperationException("Implementar conforme necessário");
    }

    @Override
    public GestorDataDTO obterGestorPorEmail(String email) {
        throw new UnsupportedOperationException("Implementar conforme necessário");
    }

    @Override
    public GestorDataDTO incluirGestor(NovoGestorDTO novoGestor) {
        throw new UnsupportedOperationException("Implementar conforme necessário");
    }

    @Override
    public void deletarGestor(Long id) {
        gestorRepository.deleteById(id);
    }

    @Override
    public LoginDataDTO obterLoginPorLogin(String login) {
        var loginEntity = loginRepository.findByLogin(login)
                .orElse(null);

        if (loginEntity == null) {
            return null;
        }

        return mapToLoginDataDTO(loginEntity);
    }

    @Override
    public void atualizarLogin(Long id, String novaSenha) {
        var loginEntity = loginRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Login não encontrado"));

        loginEntity.setSenha(novaSenha);
        loginRepository.save(loginEntity);
    }
}