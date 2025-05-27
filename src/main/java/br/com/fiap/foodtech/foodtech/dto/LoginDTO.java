package br.com.fiap.foodtech.foodtech.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record LoginDTO(
        @NotNull(message = "O login não pode ser nulo")
        String login,
        @NotNull(message = "A senha não pode ser nula")
        String senha) {
}