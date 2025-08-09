package br.com.fiap.foodtech.foodtech.core.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorTest {

    @Test
    @DisplayName("Deve criar gestor válido")
    void deveCriarGestorValido() {

        Login login = new Login("gestor123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        Gestor gestor = new Gestor("Maria Silva", "maria@email.com", "GESTOR", endereco, login);

        assertNotNull(gestor);
        assertEquals("Maria Silva", gestor.getNome());
        assertEquals("maria@email.com", gestor.getEmail());
        assertEquals("GESTOR", gestor.getTipoUsuario());
        assertEquals(login, gestor.getLogin());
        assertEquals(endereco, gestor.getEndereco());
    }

    @Test
    @DisplayName("Deve criar gestor com ID específico")
    void deveCriarGestorComId() {

        Login login = new Login("gestor123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        Gestor gestor = new Gestor(1L, "Maria Silva", "maria@email.com", "GESTOR", endereco, login);

        assertEquals(1L, gestor.getId());
        assertEquals("GESTOR", gestor.getTipoUsuario());
    }

    @Test
    @DisplayName("Deve lançar exceção para tipo de usuário inválido")
    void deveLancarExcecaoParaTipoUsuarioInvalido() {

        Login login = new Login("gestor123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                new Gestor(1L, "Maria Silva", "maria@email.com", "CLIENTE", endereco, login));
    }

    @Test
    @DisplayName("Deve validar campos obrigatórios como Cliente")
    void deveValidarCamposObrigatorios() {

        Login login = new Login("gestor123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                new Gestor(null, "maria@email.com", "GESTOR", endereco, login));

        assertThrows(IllegalArgumentException.class, () ->
                new Gestor("Maria Silva", null, "GESTOR", endereco, login));

        assertThrows(IllegalArgumentException.class, () ->
                new Gestor("Maria Silva", "maria@email.com", null, endereco, login));

        assertThrows(IllegalArgumentException.class, () ->
                new Gestor("Maria Silva", "maria@email.com", "GESTOR",  null, login));

        assertThrows(IllegalArgumentException.class, () ->
                new Gestor("Maria Silva", "maria@email.com", "GESTOR",  endereco, null));
    }
}