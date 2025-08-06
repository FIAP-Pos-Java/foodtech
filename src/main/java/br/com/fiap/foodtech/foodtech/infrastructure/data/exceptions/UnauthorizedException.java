package br.com.fiap.foodtech.foodtech.infrastructure.data.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

}
