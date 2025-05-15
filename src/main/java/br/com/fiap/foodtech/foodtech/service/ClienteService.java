package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> buscarCliente() {
        return this.clienteRepository.findAll();
    }

    public Cliente salvandoCliente(Cliente cliente) {
        cliente.setDataUltimaAlteracao(LocalDateTime.now());
        return clienteRepository.save(cliente);
    }

    public void atualizarCliente(Long id, Cliente cliente) {
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
        this.clienteRepository.deleteById(id);
    }
}
