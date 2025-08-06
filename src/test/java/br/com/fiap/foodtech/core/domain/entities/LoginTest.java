package br.com.fiap.foodtech.core.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    @DisplayName("Deve criar login válido")
    void deveCriarLoginValido() {

        Login login = Login.create("usuario123", "senha123");

        assertNotNull(login);
        assertEquals("usuario123", login.getLogin());
        assertEquals("senha123", login.getSenha());
    }

    @Test
    @DisplayName("Deve criar login com ID específico")
    void deveCriarLoginComId() {

        Login login = Login.create(1L, "usuario123", "senha123");

        assertEquals(1L, login.getId());
        assertEquals("usuario123", login.getLogin());
        assertEquals("senha123", login.getSenha());
    }

    @Test
    @DisplayName("Deve lançar exceção para login nulo")
    void deveLancarExcecaoParaLoginNulo() {

        assertThrows(IllegalArgumentException.class, () ->
                Login.create(null, "senha123"));
    }

    @Test
    @DisplayName("Deve lançar exceção para login vazio")
    void deveLancarExcecaoParaLoginVazio() {

        assertThrows(IllegalArgumentException.class, () ->
                Login.create("", "senha123"));
    }

    @Test
    @DisplayName("Deve lançar exceção para login muito curto")
    void deveLancarExcecaoParaLoginMuitoCurto() {

        assertThrows(IllegalArgumentException.class, () ->
                Login.create("ab", "senha123"));
    }

    @Test
    @DisplayName("Deve lançar exceção para senha nula")
    void deveLancarExcecaoParaSenhaNula() {

        assertThrows(IllegalArgumentException.class, () ->
                Login.create("usuario123", null));
    }

    @Test
    @DisplayName("Deve lançar exceção para senha vazia")
    void deveLancarExcecaoParaSenhaVazia() {

        assertThrows(IllegalArgumentException.class, () ->
                Login.create("usuario123", ""));
    }

    @Test
    @DisplayName("Deve lançar exceção para senha muito curta")
    void deveLancarExcecaoParaSenhaMuitoCurta() {

        assertThrows(IllegalArgumentException.class, () ->
                Login.create("usuario123", "123"));
    }

    @Test
    @DisplayName("Deve alterar senha com sucesso")
    void deveAlterarSenhaComSucesso() {

        Login login = Login.create("usuario123", "senhaAntiga");

        login.alterarSenha("novaSenha123");

        assertEquals("novaSenha123", login.getSenha());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar alterar para mesma senha")
    void deveLancarExcecaoAoAlterarParaMesmaSenha() {

        Login login = Login.create("usuario123", "senha123");

        assertThrows(IllegalArgumentException.class, () ->
                login.alterarSenha("senha123"));
    }

    @Test
    @DisplayName("Deve lançar exceção para nova senha inválida")
    void deveLancarExcecaoParaNovaSenhaInvalida() {

        Login login = Login.create("usuario123", "senha123");

        assertThrows(IllegalArgumentException.class, () ->
                login.alterarSenha("123"));
    }

    @Test
    @DisplayName("Deve lançar exceção para ID inválido")
    void deveLancarExcecaoParaIdInvalido() {

        assertThrows(IllegalArgumentException.class, () ->
                Login.create(0L, "usuario123", "senha123"));

        assertThrows(IllegalArgumentException.class, () ->
                Login.create(-1L, "usuario123", "senha123"));
    }
}