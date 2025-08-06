package br.com.fiap.foodtech.foodtech.core.dtos;

public record AlterarSenhaDTO(
        String login,
        String senhaAtual,
        String novaSenha
) { }
