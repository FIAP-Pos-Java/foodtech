package br.com.fiap.foodtech.core.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GestorTest {

    @Test
    @DisplayName("Deve criar gestor válido")
    void deveCriarGestorValido() {

        Login login = Login.create("gestor123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        Gestor gestor = Gestor.create("Maria Silva", "maria@email.com", login, endereco);

        assertNotNull(gestor);
        assertEquals("Maria Silva", gestor.getNome());
        assertEquals("maria@email.com", gestor.getEmail());
        assertEquals("DONODERESTAURANTE", gestor.getTipoUsuario());
        assertEquals(login, gestor.getLogin());
        assertEquals(endereco, gestor.getEndereco());
    }

    @Test
    @DisplayName("Deve criar gestor com ID específico")
    void deveCriarGestorComId() {

        Login login = Login.create("gestor123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        Gestor gestor = Gestor.create(1L, "Maria Silva", "maria@email.com", "DONODERESTAURANTE", login, endereco);

        assertEquals(1L, gestor.getId());
        assertEquals("DONODERESTAURANTE", gestor.getTipoUsuario());
    }

    @Test
    @DisplayName("Deve lançar exceção para tipo de usuário inválido")
    void deveLancarExcecaoParaTipoUsuarioInvalido() {

        Login login = Login.create("gestor123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                Gestor.create(1L, "Maria Silva", "maria@email.com", "CLIENTE", login, endereco));
    }

    @Test
    @DisplayName("Deve validar campos obrigatórios como Cliente")
    void deveValidarCamposObrigatorios() {

        Login login = Login.create("gestor123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                Gestor.create(null, "maria@email.com", login, endereco));

        assertThrows(IllegalArgumentException.class, () ->
                Gestor.create("Maria Silva", null, login, endereco));

        assertThrows(IllegalArgumentException.class, () ->
                Gestor.create("Maria Silva", "maria@email.com", null, endereco));

        assertThrows(IllegalArgumentException.class, () ->
                Gestor.create("Maria Silva", "maria@email.com", login, null));
    }
}