package br.com.fiap.foodtech.foodtech.dto;

import br.com.fiap.foodtech.foodtech.entities.Endereco;

public record EnderecoDTO(String logradouro, String numero, String bairro, String cidade, String estado, String cep) {

    public Endereco mapearEndereco() {
        return new Endereco(logradouro, numero, bairro, cidade, estado, cep);
    }

}
