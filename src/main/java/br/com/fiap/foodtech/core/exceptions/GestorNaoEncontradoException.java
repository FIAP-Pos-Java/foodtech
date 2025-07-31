package br.com.fiap.foodtech.core.exceptions;

public class GestorNaoEncontradoException extends RuntimeException {
    public GestorNaoEncontradoException(String message) {
        super(message);
    }
}