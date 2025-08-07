package br.com.fiap.foodtech.foodtech.core.exceptions.cliente;

import br.com.fiap.foodtech.foodtech.core.exceptions.ResourceAlreadyExistsException;

public class ClienteJaExistenteException extends ResourceAlreadyExistsException {
    public ClienteJaExistenteException(String message) {
        super(message);
    }
}
