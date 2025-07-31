package br.com.fiap.foodtech.core.dto;

public record EnderecoDataDTO(
        Long id,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep
) {}