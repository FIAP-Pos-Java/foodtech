package br.com.fiap.foodtech.core.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    @DisplayName("Deve criar cliente válido com todos os dados")
    void deveCriarClienteComDadosValidos() {

        Login login = Login.create("user123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        Cliente cliente = Cliente.create("João Silva", "joao@email.com", login, endereco);

        assertNotNull(cliente);
        assertEquals("João Silva", cliente.getNome());
        assertEquals("joao@email.com", cliente.getEmail());
        assertEquals("CLIENTE", cliente.getTipoUsuario());
        assertEquals(login, cliente.getLogin());
        assertEquals(endereco, cliente.getEndereco());
    }

    @Test
    @DisplayName("Deve criar cliente com ID específico")
    void deveCriarClienteComId() {

        Login login = Login.create("user123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        Cliente cliente = Cliente.create(1L, "João Silva", "joao@email.com", "CLIENTE", login, endereco);

        assertEquals(1L, cliente.getId());
        assertEquals("CLIENTE", cliente.getTipoUsuario());
    }

    @Test
    @DisplayName("Deve lançar exceção para nome nulo")
    void deveLancarExcecaoParaNomeNulo() {

        Login login = Login.create("user123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                Cliente.create(null, "joao@email.com", login, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para nome vazio")
    void deveLancarExcecaoParaNomeVazio() {

        Login login = Login.create("user123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                Cliente.create("", "joao@email.com", login, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para nome muito curto")
    void deveLancarExcecaoParaNomeMuitoCurto() {

        Login login = Login.create("user123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                Cliente.create("J", "joao@email.com", login, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para email nulo")
    void deveLancarExcecaoParaEmailNulo() {

        Login login = Login.create("user123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                Cliente.create("João Silva", null, login, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para login nulo")
    void deveLancarExcecaoParaLoginNulo() {

        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                Cliente.create("João Silva", "joao@email.com", null, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para endereco nulo")
    void deveLancarExcecaoParaEnderecoNulo() {

        Login login = Login.create("user123", "senha123");

        assertThrows(IllegalArgumentException.class, () ->
                Cliente.create("João Silva", "joao@email.com", login, null));
    }

    @Test
    @DisplayName("Deve lançar exceção para tipo de usuário inválido")
    void deveLancarExcecaoParaTipoUsuarioInvalido() {

        Login login = Login.create("user123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                Cliente.create(1L, "João Silva", "joao@email.com", "ADMIN", login, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para ID zero ou negativo")
    void deveLancarExcecaoParaIdInvalido() {

        Login login = Login.create("user123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                Cliente.create(0L, "João Silva", "joao@email.com", "CLIENTE", login, endereco));

        assertThrows(IllegalArgumentException.class, () ->
                Cliente.create(-1L, "João Silva", "joao@email.com", "CLIENTE", login, endereco));
    }
}