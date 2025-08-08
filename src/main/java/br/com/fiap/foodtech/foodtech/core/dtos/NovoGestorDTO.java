package br.com.fiap.foodtech.foodtech.core.dtos;

public record NovoGestorDTO(
        String nome,
        String email,
        String tipoUsuario,
        String login,
        String senha,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep
) { }
