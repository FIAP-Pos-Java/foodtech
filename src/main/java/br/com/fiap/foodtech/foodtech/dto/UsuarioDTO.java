package br.com.fiap.foodtech.foodtech.dto;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.entities.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UsuarioDTO(

        @NotEmpty(message = "O nome do usuário é obrigatório")
        String nome,

        @Email(message = "Insira um e-mail válido")
        @NotEmpty(message = "O email do usuário é obrigatório")
        String email,

        String tipoUsuario,

        @Valid
        LoginDTO login,

        @Valid
        EnderecoDTO endereco) {

    public Cliente mapearCliente() {
        return new Cliente(nome, email, tipoUsuario, login.mapearLogin(), endereco.mapearEndereco());
    }

    public Gestor mapearGestor() {
        return new Gestor(nome, email, tipoUsuario, login.mapearLogin(), endereco.mapearEndereco());
    }

}
