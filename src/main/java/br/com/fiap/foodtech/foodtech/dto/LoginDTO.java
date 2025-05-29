package br.com.fiap.foodtech.foodtech.dto;

import br.com.fiap.foodtech.foodtech.entities.Login;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record LoginDTO(Long id, String login, String senha) {

        public Login mapearLogin(){
                return new Login(login, senha);
        }
}