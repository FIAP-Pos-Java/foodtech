package br.com.fiap.foodtech.foodtech.dto;

import br.com.fiap.foodtech.foodtech.entities.Login;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record LoginDTO(String login, String senha, String novaSenha) {

        public Login mapearLogin(){
                return new Login(login, senha);
        }
}