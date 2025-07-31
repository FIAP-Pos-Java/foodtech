package br.com.fiap.foodtech.core.dto;

public record EnderecoDTO(
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep
) {}