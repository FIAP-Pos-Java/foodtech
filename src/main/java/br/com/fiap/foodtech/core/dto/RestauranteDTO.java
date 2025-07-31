package br.com.fiap.foodtech.core.dto;

import java.time.LocalTime;

public record RestauranteDTO(
        Long id,
        String nome,
        String tipoCozinha,
        LocalTime horarioAbertura,
        LocalTime horarioFechamento,
        Long idGestor,
        String nomeGestor,
        EnderecoDTO endereco
) {}