package br.com.fiap.foodtech.core.dto;

public record GestorDataDTO(
        Long id,
        String nome,
        String email,
        String tipoUsuario,
        LoginDataDTO loginData,
        EnderecoDataDTO enderecoData
) {}
