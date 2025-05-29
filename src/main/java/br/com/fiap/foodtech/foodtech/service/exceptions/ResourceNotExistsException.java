package br.com.fiap.foodtech.foodtech.service.exceptions;

public class ResourceNotExistsException extends RuntimeException {

    public ResourceNotExistsException(String message) {
        super(message);
    }

}
