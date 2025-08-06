package br.com.fiap.foodtech.foodtech.infrastructure.data.exceptions;

public class ResourceNotExistsException extends RuntimeException {

    public ResourceNotExistsException(String message) {
        super(message);
    }

}
