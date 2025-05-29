package br.com.fiap.foodtech.foodtech.dto;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UsuarioDTO(

        @NotEmpty(message = "O nome do usuário é obrigatório")
        String nome,

        @NotEmpty(message = "O email do usuário é obrigatório")
        @Email(message = "Insira um e-mail válido")
        String email,

        @NotEmpty(message = "O login do usuário é obrigatório")
        String login,

        @NotEmpty(message = "A senha do usuário é obrigatória")
        String senha,

        @Valid EnderecoDTO endereco) {

    public Cliente mapearCliente() {
        Cliente cliente = new Cliente(nome, email, login, senha);
        cliente.setEndereco(endereco.mapearEndereco());
        return cliente;
    }

    public Gestor mapearGestor() {
        Gestor gestor = new Gestor(nome, email, login, senha);
        gestor.setEndereco(endereco.mapearEndereco());
        return gestor;
    }

}
