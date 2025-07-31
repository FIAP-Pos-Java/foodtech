package br.com.fiap.foodtech.core.dto;

import java.time.LocalTime;

public record NovoRestauranteDTO(
        String nome,
        String tipoCozinha,
        LocalTime horarioAbertura,
        LocalTime horarioFechamento,
        Long idGestor,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep
) {}
