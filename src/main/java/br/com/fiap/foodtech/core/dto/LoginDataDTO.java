package br.com.fiap.foodtech.core.dto;

public record LoginDataDTO(
        Long id,
        String login,
        String senha
) {}