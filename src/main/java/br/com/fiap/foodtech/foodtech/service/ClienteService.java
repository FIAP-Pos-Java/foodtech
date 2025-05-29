package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.dto.UsuarioDTO;
import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Endereco;
import br.com.fiap.foodtech.foodtech.entities.Login;
import br.com.fiap.foodtech.foodtech.repositories.ClienteRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import br.com.fiap.foodtech.foodtech.service.exceptions.UnauthorizedException;
import br.com.fiap.foodtech.foodtech.validation.ClienteValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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

    public void saveCliente(UsuarioDTO usuarioDTO) {
        Cliente novoCliente = usuarioDTO.mapearCliente();
//        this.clienteValidator.validarEmail(novoCliente);
        this.clienteRepository.save(novoCliente);
    }

    public void updateCliente(Long id, UsuarioDTO usuarioDTO) {
        Cliente clienteExistente = this.clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado. ID: " + id));

        Cliente clienteAtualizado = usuarioDTO.mapearCliente();
        Login loginAtualizado = usuarioDTO.mapearCliente().getLogin();

        Login loginExistente = clienteExistente.getLogin();
        if(loginExistente == null){
            loginExistente = new Login();
            loginExistente.setLogin(loginAtualizado.getLogin());
        }

        Endereco enderecoExistente = clienteExistente.getEndereco();
        if(enderecoExistente == null){
            enderecoExistente = new Endereco();
            clienteExistente.setEndereco(enderecoExistente);
        }

        clienteAtualizado.setId(id);
        clienteAtualizado.getLogin().setId(loginExistente.getId());
        clienteAtualizado.getEndereco().setId(enderecoExistente.getId());
        this.clienteRepository.save(clienteAtualizado);
    }

    public void deleteCliente(Long id) {
        this.clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado. ID: " + id));
        this.clienteRepository.deleteById(id);
    }
}
