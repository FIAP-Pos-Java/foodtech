package br.com.fiap.foodtech.foodtech.dtos;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Endereco;

import java.time.LocalDateTime;

public record SalvarClienteDTO(String nome, String email, String login, String senha, Endereco endereco) {

    public Cliente mapearSalvarCliente() {
        Cliente cliente = new Cliente();

        cliente.setNome(this.nome);
        cliente.setEmail(this.email);
        cliente.setLogin(this.login);
        cliente.setSenha(this.senha);
        cliente.setDataUltimaAlteracao(LocalDateTime.now());

        Endereco endereco = new Endereco();
        endereco.setLogradouro(this.endereco.getLogradouro());
        endereco.setNumero(this.endereco.getNumero());
        endereco.setBairro(this.endereco.getBairro());
        endereco.setCidade(this.endereco.getCidade());
        endereco.setEstado(this.endereco.getEstado());
        endereco.setCep(this.endereco.getCep());

        cliente.setEndereco(endereco);

        return cliente;
    }
}
