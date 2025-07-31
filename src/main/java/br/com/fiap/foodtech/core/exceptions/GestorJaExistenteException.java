package br.com.fiap.foodtech.core.exceptions;

public class GestorJaExistenteException extends RuntimeException {
    public GestorJaExistenteException(String message) {
        super(message);
    }
}