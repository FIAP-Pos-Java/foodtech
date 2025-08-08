package br.com.fiap.foodtech.foodtech.core.dtos;

import java.time.LocalTime;

public record NovoRestauranteDTO(
        String nome,
        String tipoCozinha,
        LocalTime horarioAbertura,
        LocalTime horarioFechamento,
        Long idGestor,
        NovoEnderecoDTO endereco
) { }
