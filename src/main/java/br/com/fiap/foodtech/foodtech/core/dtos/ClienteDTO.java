package br.com.fiap.foodtech.foodtech.core.dtos;

public record ClienteDTO(
        Long id,
        String nome,
        String email,
        String tipoUsuario,
        String loginOfuscado,
        EnderecoDTO endereco
) { }
