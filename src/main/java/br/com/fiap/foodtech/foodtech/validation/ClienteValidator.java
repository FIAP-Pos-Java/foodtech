package br.com.fiap.foodtech.foodtech.validation;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Login;
import br.com.fiap.foodtech.foodtech.repositories.ClienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ClienteValidator {

//    private final ClienteRepository clienteRepository;
//
//    public ClienteValidator(ClienteRepository clienteRepository) {
//        this.clienteRepository = clienteRepository;
//    }
//
//    private boolean existeClienteComEmail(String email){
//        return clienteRepository.existsClienteByEmail(email);
//    }
//
//    private boolean existeIdCliente(Long id){
//        return clienteRepository.existsClienteById(id);
//    }
//
//    public void validarEmail(Cliente cliente){
//        if (existeClienteComEmail(cliente.getEmail())) {
//            throw new IllegalArgumentException("esse cliente ja existe, entrar em contato com o suporte");
//        }
//    }
//
//    public void validarId(Long id){
//        if(!existeIdCliente(id)){
//            throw new IllegalArgumentException("esse cliente não existe na base de dados");
//        }
//    }
}
