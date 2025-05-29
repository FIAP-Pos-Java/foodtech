package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.entities.Login;
import br.com.fiap.foodtech.foodtech.repositories.LoginRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotExistsException;
import br.com.fiap.foodtech.foodtech.validation.LoginValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final LoginValidator loginValidator;

    public LoginService(LoginRepository loginRepository, LoginValidator loginValidator) {
        this.loginRepository = loginRepository;
        this.loginValidator = loginValidator;
    }

    public Optional<Login> findLogin(Long id) {
        return loginRepository.findById(id);
    }

    public void updateLogin(Long id, Login login) {
        Optional<Login> loginOptional = loginRepository.findById(id);

        if(loginOptional.isPresent()) {
            Login loginAtualizado = loginOptional.get();

            if(login.getLogin() != null){
                loginAtualizado.setLogin(login.getLogin());
            }

            if(login.getLogin() != null){
                loginAtualizado.setSenha(login.getSenha());
            }
            this.loginValidator.validarLogin(loginAtualizado);
            this.loginRepository.save(loginAtualizado);
        }else{
            throw new ResourceNotExistsException("login não encontrado");
        }
    }
}
