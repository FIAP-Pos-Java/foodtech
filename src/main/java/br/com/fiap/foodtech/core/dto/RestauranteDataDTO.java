package br.com.fiap.foodtech.core.dto;

import java.time.LocalTime;

public record RestauranteDataDTO(
        Long id,
        String nome,
        String tipoCozinha,
        LocalTime horarioAbertura,
        LocalTime horarioFechamento,
        GestorDataDTO gestorData,
        EnderecoDataDTO enderecoData
) {}