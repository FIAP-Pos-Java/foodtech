package br.com.fiap.foodtech.foodtech.dto;

import br.com.fiap.foodtech.foodtech.entities.Endereco;
import jakarta.validation.constraints.NotEmpty;

public record EnderecoDTO(

        String logradouro,

        @NotEmpty(message = "O número é obrigatório")
        String numero,

        String bairro,

        @NotEmpty(message = "O cep é obrigatório")
        String cep) {

    public Endereco mapearEndereco() {
        return new Endereco(logradouro, numero, bairro, cep);
    }

}
