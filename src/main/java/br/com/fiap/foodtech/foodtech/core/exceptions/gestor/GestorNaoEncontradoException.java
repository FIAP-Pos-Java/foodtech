package br.com.fiap.foodtech.foodtech.core.exceptions.gestor;

import br.com.fiap.foodtech.foodtech.core.exceptions.ResourceNotFoundException;

public class GestorNaoEncontradoException extends ResourceNotFoundException {
    public GestorNaoEncontradoException(String message) {
        super(message);
    }
}
