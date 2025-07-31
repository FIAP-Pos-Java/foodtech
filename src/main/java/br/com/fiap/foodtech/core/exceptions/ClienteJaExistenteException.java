package br.com.fiap.foodtech.core.exceptions;

public class ClienteJaExistenteException extends RuntimeException {
    public ClienteJaExistenteException(String message) {
        super(message);
    }
}