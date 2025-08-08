package br.com.fiap.foodtech.foodtech.core.dtos;

public record NovoClienteDTO(
        String nome,
        String email,
        String tipoUsuario,
        NovoLoginDTO login,
        NovoEnderecoDTO endereco
) { }
