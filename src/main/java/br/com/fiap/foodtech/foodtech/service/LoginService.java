package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.dto.LoginDTO;
import br.com.fiap.foodtech.foodtech.dto.SenhaDTO;
import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Login;
import br.com.fiap.foodtech.foodtech.repositories.LoginRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotExistsException;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import br.com.fiap.foodtech.foodtech.service.exceptions.UnauthorizedException;
import br.com.fiap.foodtech.foodtech.validation.LoginValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
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

    public void validarLogin(LoginDTO loginDTO) {
        Login login = this.loginValidator.retornarLogin(loginDTO);
        this.loginValidator.validarSenha(login, loginDTO);
    }

    public void updateSenha(LoginDTO loginDTO) {
        Login login = this.loginValidator.retornarLogin(loginDTO);
        this.loginValidator.validarNovaSenha(login, loginDTO);

        login.setSenha(loginDTO.senha());
        login.setDataUltimaAlteracao(LocalDateTime.now());
        this.loginRepository.save(login);
    }

}
