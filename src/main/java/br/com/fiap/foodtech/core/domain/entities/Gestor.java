package br.com.fiap.foodtech.core.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Gestor {
    private Long id;
    private String nome;
    private String email;
    private String tipoUsuario;
    private Login login;
    private Endereco endereco;

    public static Gestor create(String nome, String email, Login login, Endereco endereco) {
        Gestor gestor = new Gestor();
        gestor.setNome(nome);
        gestor.setEmail(email);
        gestor.setTipoUsuario("DONODERESTAURANTE");
        gestor.setLogin(login);
        gestor.setEndereco(endereco);
        return gestor;
    }

    public static Gestor create(Long id, String nome, String email, String tipoUsuario, Login login, Endereco endereco) {
        Gestor gestor = new Gestor();
        gestor.setId(id);
        gestor.setNome(nome);
        gestor.setEmail(email);
        gestor.setTipoUsuario(tipoUsuario);
        gestor.setLogin(login);
        gestor.setEndereco(endereco);
        return gestor;
    }

    private void setId(Long id) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero");
        }
        this.id = id;
    }

    private void setNome(String nome) {
        validateNome(nome);
        this.nome = nome;
    }

    private void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    private void setTipoUsuario(String tipoUsuario) {
        if (!"DONODERESTAURANTE".equals(tipoUsuario)) {
            throw new IllegalArgumentException("Tipo de usuário inválido para Gestor");
        }
        this.tipoUsuario = tipoUsuario;
    }

    private void setLogin(Login login) {
        if (login == null) {
            throw new IllegalArgumentException("Login é obrigatório");
        }
        this.login = login;
    }

    private void setEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço é obrigatório");
        }
        this.endereco = endereco;
    }

    private static void validateNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (nome.length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
    }
}