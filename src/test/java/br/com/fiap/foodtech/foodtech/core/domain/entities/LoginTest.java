package br.com.fiap.foodtech.foodtech.core.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    @DisplayName("Deve criar login válido")
    void deveCriarLoginValido() {

        Login login = new Login("usuario123", "senha123");

        assertNotNull(login);
        assertEquals("usuario123", login.getLogin());
        assertEquals("senha123", login.getSenha());
    }

    @Test
    @DisplayName("Deve criar login com ID específico")
    void deveCriarLoginComId() {

        Login login = new Login(1L, "usuario123", "senha123");

        assertEquals(1L, login.getId());
        assertEquals("usuario123", login.getLogin());
        assertEquals("senha123", login.getSenha());
    }

    @Test
    @DisplayName("Deve lançar exceção para login nulo")
    void deveLancarExcecaoParaLoginNulo() {

        assertThrows(IllegalArgumentException.class, () ->
                new Login(1L, null, "senha123"));
    }

    @Test
    @DisplayName("Deve lançar exceção para login vazio")
    void deveLancarExcecaoParaLoginVazio() {

        assertThrows(IllegalArgumentException.class, () ->
                new Login("", "senha123"));
    }

    @Test
    @DisplayName("Deve lançar exceção para login muito curto")
    void deveLancarExcecaoParaLoginMuitoCurto() {

        assertThrows(IllegalArgumentException.class, () ->
                new Login("ab", "senha123"));
    }

    @Test
    @DisplayName("Deve lançar exceção para senha nula")
    void deveLancarExcecaoParaSenhaNula() {

        assertThrows(IllegalArgumentException.class, () ->
                new Login("usuario123", null));
    }

    @Test
    @DisplayName("Deve lançar exceção para senha vazia")
    void deveLancarExcecaoParaSenhaVazia() {

        assertThrows(IllegalArgumentException.class, () ->
                new Login("usuario123", ""));
    }

    @Test
    @DisplayName("Deve lançar exceção para senha muito curta")
    void deveLancarExcecaoParaSenhaMuitoCurta() {

        assertThrows(IllegalArgumentException.class, () ->
                new Login("usuario123", "123"));
    }

    @Test
    @DisplayName("Deve lançar exceção para ID inválido")
    void deveLancarExcecaoParaIdInvalido() {

        assertThrows(IllegalArgumentException.class, () ->
                new Login(0L, "usuario123", "senha123"));

        assertThrows(IllegalArgumentException.class, () ->
                new Login(-1L, "usuario123", "senha123"));
    }
}
