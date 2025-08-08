package br.com.fiap.foodtech.foodtech.core.dtos;

import java.time.LocalTime;

public record NovoRestauranteComGestorDTO(
        String nome,
        String tipoCozinha,
        LocalTime horarioAbertura,
        LocalTime horarioFechamento,
        GestorDataDTO gestor,
        NovoEnderecoDTO endereco
) { }
