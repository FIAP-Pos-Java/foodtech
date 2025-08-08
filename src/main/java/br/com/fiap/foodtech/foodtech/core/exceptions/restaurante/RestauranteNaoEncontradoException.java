package br.com.fiap.foodtech.foodtech.core.exceptions.restaurante;

import br.com.fiap.foodtech.foodtech.core.exceptions.ResourceNotFoundException;

public class RestauranteNaoEncontradoException extends ResourceNotFoundException {
    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }
}
