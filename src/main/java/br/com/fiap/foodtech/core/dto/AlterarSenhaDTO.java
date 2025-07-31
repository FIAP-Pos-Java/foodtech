package br.com.fiap.foodtech.core.dto;

public record AlterarSenhaDTO(
        String login,
        String senhaAtual,
        String novaSenha
) {}