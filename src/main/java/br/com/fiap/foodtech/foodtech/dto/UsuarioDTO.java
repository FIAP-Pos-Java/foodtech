package br.com.fiap.foodtech.foodtech.dto;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.entities.Usuario;

public record UsuarioDTO(String nome, String email, LoginDTO login, EnderecoDTO endereco) {

    public Cliente mapearCliente() {
        Cliente cliente = new Cliente(nome, email, login.mapearLogin(), endereco.mapearEndereco());
        return cliente;
    }

    public Gestor mapearGestor() {
        Gestor gestor = new Gestor(nome, email, login.mapearLogin(), endereco.mapearEndereco());
        return gestor;
    }

}
