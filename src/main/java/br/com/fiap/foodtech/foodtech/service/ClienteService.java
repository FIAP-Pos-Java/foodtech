package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.repositories.ClienteRepository;
import br.com.fiap.foodtech.foodtech.validation.ClienteValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteValidator clienteValidator;

    public ClienteService(ClienteRepository clienteRepository, ClienteValidator clienteValidator) {
        this.clienteRepository = clienteRepository;
        this.clienteValidator = clienteValidator;
    }

    public List<Cliente> buscarCliente() {
        return this.clienteRepository.findAll();
    }

    public Cliente salvandoCliente(Cliente cliente) {
        cliente.setDataUltimaAlteracao(LocalDateTime.now());
        clienteValidator.validarLoginAndEmail(cliente);
        return clienteRepository.save(cliente);
    }

    public void atualizarCliente(Long id, Cliente cliente) {
        this.clienteValidator.validarId(id);
        this.clienteRepository.findById(id).map(cli -> {
            cli.setNome(cli.getNome());
            cli.setEmail(cliente.getEmail());
            cli.setLogin(cliente.getLogin());
            cli.setSenha(cliente.getSenha());
            cli.setDataUltimaAlteracao(LocalDateTime.now());
            cli.getEndereco().setLogradouro(cliente.getEndereco().getLogradouro());
            cli.getEndereco().setNumero(cliente.getEndereco().getNumero());
            cli.getEndereco().setComplemento(cliente.getEndereco().getComplemento());
            cli.getEndereco().setBairro(cliente.getEndereco().getBairro());
            cli.getEndereco().setCidade(cliente.getEndereco().getCidade());
            cli.getEndereco().setEstado(cliente.getEndereco().getEstado());
            cli.getEndereco().setCep(cliente.getEndereco().getCep());
            return clienteRepository.save(cli);
        }).orElse(null);
    }

    public void deletandoCliente(Long id) {
        this.clienteValidator.validarId(id);
        this.clienteRepository.deleteById(id);
    }

    public Cliente validarLogin(String login, String senha) {
        Cliente cliente = clienteRepository.findByLogin(login);
        if (cliente == null) {
            throw new IllegalArgumentException("Login inválido");
        }
        if (!cliente.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha inválida");
        }
        return cliente;
    }
}
