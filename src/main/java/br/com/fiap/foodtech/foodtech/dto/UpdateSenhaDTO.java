package br.com.fiap.foodtech.foodtech.dto;

import jakarta.validation.constraints.NotEmpty;

public record UpdateSenhaDTO(

        @NotEmpty(message = "O login do usuário é obrigatório")
        String login,

        @NotEmpty(message = "A senha do usuário é obrigatória")
        String senha,

        @NotEmpty(message = "A nova senha do usuário é obrigatória")
        String novaSenha ) {
}
