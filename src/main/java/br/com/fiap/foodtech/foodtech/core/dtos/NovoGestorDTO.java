package br.com.fiap.foodtech.foodtech.core.dtos;

public record NovoGestorDTO(
        String nome,
        String email,
        String tipoUsuario,
        NovoLoginDTO login,
        NovoEnderecoDTO endereco
) { }
