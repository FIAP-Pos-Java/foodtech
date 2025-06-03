package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.dto.LoginDTO;
import br.com.fiap.foodtech.foodtech.dto.UpdateSenhaDTO;
import br.com.fiap.foodtech.foodtech.entities.Login;
import br.com.fiap.foodtech.foodtech.repositories.LoginRepository;
import br.com.fiap.foodtech.foodtech.validation.LoginValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final LoginValidator loginValidator;

    public LoginService(LoginRepository loginRepository, LoginValidator loginValidator) {
        this.loginRepository = loginRepository;
        this.loginValidator = loginValidator;
    }

    public void validarLogin(LoginDTO loginDTO) {
        Login login = this.loginValidator.retornarLoginValido(loginDTO);
        this.loginValidator.validarSenha(login, loginDTO);
    }

    public void updateSenha(UpdateSenhaDTO updateSenhaDTO) {
        LoginDTO loginDTO = new LoginDTO(updateSenhaDTO.login(), updateSenhaDTO.senha());

        Login login = this.loginValidator.retornarLoginValido(loginDTO);
        this.loginValidator.validarNovaSenha(login, updateSenhaDTO);

        login.setSenha(updateSenhaDTO.novaSenha());
        login.setDataUltimaAlteracao(LocalDateTime.now());
        this.loginRepository.save(login);
    }

}
