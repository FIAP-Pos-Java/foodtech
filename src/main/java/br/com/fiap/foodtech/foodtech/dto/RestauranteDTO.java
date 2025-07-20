package br.com.fiap.foodtech.foodtech.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record RestauranteDTO(
        Long id,

        @NotEmpty(message = "O nome do restaurante é obrigatório")
        String nome,

        @NotEmpty(message = "O tipo de cozinha é obrigatório")
        String tipoCozinha,

        @NotNull(message = "O horário de abertura é obrigatório")
        LocalTime horarioAbertura,

        @NotNull(message = "O horário de fechamento é obrigatório")
        LocalTime horarioFechamento,

        @NotNull(message = "O ID do dono do restaurante é obrigatório")
        Long idDonoRestaurante,

        @NotNull(message = "O endereço é obrigatório")
        EnderecoDTO endereco
) {
}
