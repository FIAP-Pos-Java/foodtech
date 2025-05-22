package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.dtos.AtualizarClienteDTO;
import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Endereco;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.repositories.ClienteRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import br.com.fiap.foodtech.foodtech.service.exceptions.UnauthorizedException;
import br.com.fiap.foodtech.foodtech.validation.ClienteValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteValidator clienteValidator;

    public ClienteService(ClienteRepository clienteRepository, ClienteValidator clienteValidator) {
        this.clienteRepository = clienteRepository;
        this.clienteValidator = clienteValidator;
    }

    public Page<Cliente> findAllClientes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.clienteRepository.findAll(pageable);
    }

    public Cliente findCliente(Long id) {
        return this.clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado. ID: " + id));
    }

    public void saveCliente(Cliente cliente) {
        this.clienteValidator.validarLoginAndEmail(cliente);
        this.clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, AtualizarClienteDTO atualizarClienteDTO) {
        Cliente clienteExistente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado. ID: " + id));

        Endereco enderecoExistente = clienteExistente.getEndereco();
        if(enderecoExistente == null){
            enderecoExistente = new Endereco();
            clienteExistente.setEndereco(enderecoExistente);
        }

        Cliente clienteAtualizado = atualizarClienteDTO.mapearAtualizarCliente();
        clienteAtualizado.setId(id);
        clienteAtualizado.getEndereco().setId(enderecoExistente.getId());

        return this.clienteRepository.save(clienteAtualizado);
    }

    public void deleteCliente(Long id) {
        this.clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado. ID: " + id));
        this.clienteRepository.deleteById(id);
    }

    public Cliente validarLogin(String login, String senha) {
        Cliente cliente = clienteRepository.findByLogin(login);
        if (cliente == null) {
            throw new UnauthorizedException("Login inválido.");
        }
        if (!cliente.getSenha().equals(senha)) {
            throw new UnauthorizedException("Senha inválida.");
        }
        return cliente;
    }
}
