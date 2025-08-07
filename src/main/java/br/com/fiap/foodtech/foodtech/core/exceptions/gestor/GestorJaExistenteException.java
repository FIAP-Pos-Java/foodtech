package br.com.fiap.foodtech.foodtech.core.exceptions.gestor;

import br.com.fiap.foodtech.foodtech.core.exceptions.ResourceAlreadyExistsException;

public class GestorJaExistenteException extends ResourceAlreadyExistsException {
    public GestorJaExistenteException(String message) {
        super(message);
    }
}
