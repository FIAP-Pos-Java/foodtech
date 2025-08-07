package br.com.fiap.foodtech.foodtech.core.domain.entities;

public class Login {

    private Long id;
    private String login;
    private String senha;

    public Login(Long id, String login, String senha) {
        setId(id);
        setLogin(login);
        setSenha(senha);
    }

    public Login(String login, String senha) {
        setLogin(login);
        setSenha(senha);
    }

    public Login(Long id, String login) {
        setId(id);
        setLogin(login);
    }

    private void setId(Long id) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero");
        }
        this.id = id;
    }

    private void setLogin(String login) {
        validateLogin(login);
        this.login = login;
    }

    private void setSenha(String senha) {
        validateSenha(senha);
        this.senha = senha;
    }

    private static void validateLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login é obrigatório");
        }
        if (login.length() < 3) {
            throw new IllegalArgumentException("Login deve ter pelo menos 3 caracteres");
        }
    }

    private static void validateSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
        if (senha.length() < 4) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 4 caracteres");
        }
    }

    public void alterarSenha(String novaSenha) {
        validateSenha(novaSenha);
        if (this.senha.equals(novaSenha)) {
            throw new IllegalArgumentException("A nova senha deve ser diferente da senha atual");
        }
        this.senha = novaSenha;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

}
