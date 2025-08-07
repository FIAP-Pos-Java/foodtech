package br.com.fiap.foodtech.foodtech.core.exceptions.credenciais;

import br.com.fiap.foodtech.foodtech.core.exceptions.UnauthorizedException;

public class LoginInvalidoException extends UnauthorizedException {
    public LoginInvalidoException(String message) {
        super(message);
    }
}
