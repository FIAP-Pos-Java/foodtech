package br.com.fiap.foodtech.core.dto;

public record NovoClienteDTO(
        String nome,
        String email,
        String login,
        String senha,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep
) {}