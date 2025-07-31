package br.com.fiap.foodtech.core.exceptions;

public class RestauranteNaoEncontradoException extends RuntimeException {
    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }
}