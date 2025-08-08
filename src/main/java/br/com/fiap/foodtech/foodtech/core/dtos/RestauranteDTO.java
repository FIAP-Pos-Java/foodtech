package br.com.fiap.foodtech.foodtech.core.dtos;

import java.time.LocalTime;

public record RestauranteDTO(
        Long id,
        String nome,
        String tipoCozinha,
        LocalTime horarioAbertura,
        LocalTime horarioFechamento,
        EnderecoDTO endereco,
        Long idGestor,
        String nomeGestor
) { }
