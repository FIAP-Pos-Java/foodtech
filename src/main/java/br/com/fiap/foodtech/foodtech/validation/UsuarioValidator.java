package br.com.fiap.foodtech.foodtech.validation;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.entities.Usuario;
import br.com.fiap.foodtech.foodtech.repositories.ClienteRepository;
import br.com.fiap.foodtech.foodtech.repositories.GestorRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceAlreadyExistsException;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidator {

    private final ClienteRepository clienteRepository;
    private final GestorRepository gestorRepository;

    public UsuarioValidator(ClienteRepository clienteRepository, GestorRepository gestorRepository) {
        this.clienteRepository = clienteRepository;
        this.gestorRepository = gestorRepository;
    }

    private boolean existeUsuarioComEmail(Usuario usuario) {

        if (usuario instanceof Cliente) {
            return this.clienteRepository.existsClienteByEmail(usuario.getEmail());
        }else if(usuario instanceof Gestor) {
            return  this.gestorRepository.existsGestorByEmail(usuario.getEmail());
        }
        return false;
    }

    public void validarEmail(Usuario usuario) {
        if(existeUsuarioComEmail(usuario)) {
            throw new ResourceAlreadyExistsException("o usuário já existe");
        }
    }
}
