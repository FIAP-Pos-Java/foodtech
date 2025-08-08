package br.com.fiap.foodtech.foodtech.core.exceptions;

public class GestorNaoEncontradoException extends RuntimeException {
    public GestorNaoEncontradoException(String message) {
        super(message);
    }
}
