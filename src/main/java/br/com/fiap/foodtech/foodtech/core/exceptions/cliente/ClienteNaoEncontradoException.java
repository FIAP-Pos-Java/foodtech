package br.com.fiap.foodtech.foodtech.core.exceptions.cliente;

import br.com.fiap.foodtech.foodtech.core.exceptions.ResourceNotFoundException;

public class ClienteNaoEncontradoException extends ResourceNotFoundException {
    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
