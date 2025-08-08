package br.com.fiap.foodtech.foodtech.core.exceptions.credenciais;

import br.com.fiap.foodtech.foodtech.core.exceptions.UnauthorizedException;

public class CredenciaisInvalidasException extends UnauthorizedException {
    public CredenciaisInvalidasException(String message) {
        super(message);
    }
}
