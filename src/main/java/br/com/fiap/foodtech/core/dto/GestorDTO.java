package br.com.fiap.foodtech.core.dto;

public record GestorDTO(
        Long id,
        String nome,
        String email,
        String tipoUsuario,
        String loginOfuscado
) {}