package br.com.fiap.foodtech.foodtech.core.dtos;

import java.time.LocalTime;

public record RestauranteDataDTO(
        Long id,
        String nome,
        String tipoCozinha,
        LocalTime horarioAbertura,
        LocalTime horarioFechamento,
        GestorDataDTO gestor,
        EnderecoDataDTO endereco
) { }
