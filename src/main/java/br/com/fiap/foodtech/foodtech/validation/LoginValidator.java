package br.com.fiap.foodtech.foodtech.validation;

import br.com.fiap.foodtech.foodtech.dto.LoginDTO;
import br.com.fiap.foodtech.foodtech.dto.UpdateSenhaDTO;
import br.com.fiap.foodtech.foodtech.entities.Login;
import br.com.fiap.foodtech.foodtech.repositories.LoginRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import br.com.fiap.foodtech.foodtech.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoginValidator {

    private final LoginRepository loginRepository;

    public LoginValidator(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Login retornarLoginValido(LoginDTO loginDTO) {
        return this.loginRepository.findByLogin(loginDTO.login()).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não cadastrado.")
        );
    }

    public void validarSenha(Login login, LoginDTO loginDTO) {
        if (!Objects.equals(login.getSenha(), loginDTO.senha())) {
            throw new UnauthorizedException("Senha incorreta.");
        }
    }

    public void validarNovaSenha(Login login, UpdateSenhaDTO updateSenhaDTO) {
        if (!Objects.equals(login.getSenha(), updateSenhaDTO.senha())) {
            throw new UnauthorizedException("Senha atual não é a senha cadastrada.");
        }
        if (Objects.equals(login.getSenha(), updateSenhaDTO.novaSenha())) {
            throw new UnauthorizedException ("A nova senha não pode ser igual a senha atual.");
        }
    }

}
