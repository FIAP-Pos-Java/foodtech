package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.dto.LoginDTO;
import br.com.fiap.foodtech.foodtech.dto.SenhaDTO;


public interface LoginService {
    void UpdateSenha(SenhaDTO senhadto);
    void validarLogin(LoginDTO loginDTO);
}
