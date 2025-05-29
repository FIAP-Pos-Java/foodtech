package br.com.fiap.foodtech.foodtech.validation;

import br.com.fiap.foodtech.foodtech.entities.Login;
import br.com.fiap.foodtech.foodtech.repositories.LoginRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotExistsException;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    private final LoginRepository loginRepository;

    public LoginValidator(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    private boolean existeLogn(String login){
        return loginRepository.existsLoginByLogin(login);
    }

    public void validarLogin(Login login){
        if (!existeLogn(login.getLogin())) {
            throw new ResourceNotExistsException("Este login não existe");
        }
    }

}
