package br.com.fiap.foodtech.foodtech.dto;

import br.com.fiap.foodtech.foodtech.entities.Login;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record LoginDTO(

        @NotEmpty(message = "O login do usuário é obrigatório")
        String login,

        @NotEmpty(message = "A senha do usuário é obrigatória")
        String senha) {

        public Login mapearLogin(){
                return new Login(login, senha);
        }
}
