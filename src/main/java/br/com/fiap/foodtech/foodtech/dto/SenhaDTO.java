package br.com.fiap.foodtech.foodtech.dto;

import jakarta.validation.constraints.NotNull;

public record SenhaDTO(
        @NotNull(message = "O login não pode ser nulo")
        String login,
        @NotNull(message = "A senha atual não pode ser nula")
        String senhaAtual,
        @NotNull(message = "A nova senha não pode ser nula")
        String novaSenha) {
}