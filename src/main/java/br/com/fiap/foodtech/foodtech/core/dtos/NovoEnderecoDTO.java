package br.com.fiap.foodtech.foodtech.core.dtos;

public record NovoEnderecoDTO(
        Long id,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep
) { }
