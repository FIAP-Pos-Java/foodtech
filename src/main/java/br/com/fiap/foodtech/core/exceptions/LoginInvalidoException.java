package br.com.fiap.foodtech.core.exceptions;

public class LoginInvalidoException extends RuntimeException {
    public LoginInvalidoException(String message) {
        super(message);
    }
}