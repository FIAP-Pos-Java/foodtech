package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // exemplo de como salvar a data automaticamente.
    public void atualizaCliente(Cliente cliente) {
        cliente.setDataUltimaAlteracao(LocalDateTime.now());
    }
}
