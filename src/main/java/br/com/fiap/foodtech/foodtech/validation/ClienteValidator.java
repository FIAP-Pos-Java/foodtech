package br.com.fiap.foodtech.foodtech.validation;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.repositories.ClienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ClienteValidator {

    private final ClienteRepository clienteRepository;

    public ClienteValidator(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private boolean existeClienteComLogin(String login){
        return clienteRepository.existsClienteByLogin(login);
    }

    private boolean existeIdCliente(Long id){
        return clienteRepository.existsById(id);
    }

    public void validarLogin(Cliente cliente){
        if(existeClienteComLogin(cliente.getLogin())){
            throw new IllegalArgumentException("já existe um cliente com este login");
        }
    }

    public void validarId(Long id){
        if(!existeIdCliente(id)){
            throw new IllegalArgumentException("esse cliente não existe na base de dados");
        }
    }
}
