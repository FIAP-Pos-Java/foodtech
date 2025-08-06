package br.com.fiap.foodtech.foodtech.infrastructure.data.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
