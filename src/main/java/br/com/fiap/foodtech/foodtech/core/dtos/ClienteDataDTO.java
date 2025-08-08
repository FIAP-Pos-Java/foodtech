package br.com.fiap.foodtech.foodtech.core.dtos;

public record ClienteDataDTO(
        Long id,
        String nome,
        String email,
        String tipoUsuario,
        LoginDataDTO login,
        EnderecoDataDTO endereco
) { }
