package br.com.fiap.foodtech.foodtech.infrastructure.data.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

}
