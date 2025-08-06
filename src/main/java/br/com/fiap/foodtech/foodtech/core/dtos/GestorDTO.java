package br.com.fiap.foodtech.foodtech.core.dtos;

public record GestorDTO(
        Long id,
        String nome,
        String email,
        String tipoUsuario,
        String loginOfuscado
) { }
