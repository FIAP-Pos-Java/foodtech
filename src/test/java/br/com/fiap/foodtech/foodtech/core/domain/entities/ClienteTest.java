package br.com.fiap.foodtech.foodtech.core.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    @DisplayName("Deve criar cliente válido com todos os dados")
    void deveCriarClienteComDadosValidos() {

        Login login = new Login("user123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        Cliente cliente = new Cliente("João Silva", "joao@email.com", "CLIENTE", login, endereco);

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

        Login login = new Login("user123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        Cliente cliente = new Cliente(1L, "João Silva", "joao@email.com", "CLIENTE", login, endereco);

        assertEquals(1L, cliente.getId());
        assertEquals("CLIENTE", cliente.getTipoUsuario());
    }

    @Test
    @DisplayName("Deve lançar exceção para nome nulo")
    void deveLancarExcecaoParaNomeNulo() {

        Login login = new Login("user123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(null, "joao@email.com", "CLIENTE", login, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para nome vazio")
    void deveLancarExcecaoParaNomeVazio() {

        Login login = new Login("user123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente("", "joao@email.com", "CLIENTE", login, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para nome muito curto")
    void deveLancarExcecaoParaNomeMuitoCurto() {

        Login login = new Login("user123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente("J", "joao@email.com", "CLIENTE", login, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para email nulo")
    void deveLancarExcecaoParaEmailNulo() {

        Login login = new Login("user123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente("João Silva", null, "CLIENTE", login, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para login nulo")
    void deveLancarExcecaoParaLoginNulo() {

        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente("João Silva", "joao@email.com", "CLIENTE", null, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para endereco nulo")
    void deveLancarExcecaoParaEnderecoNulo() {

        Login login = new Login("user123", "senha123");

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente("João Silva", "joao@email.com", "CLIENTE", login, null));
    }

    @Test
    @DisplayName("Deve lançar exceção para tipo de usuário inválido")
    void deveLancarExcecaoParaTipoUsuarioInvalido() {

        Login login = new Login("user123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(1L, "João Silva", "joao@email.com", "ADMIN", login, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para ID zero ou negativo")
    void deveLancarExcecaoParaIdInvalido() {

        Login login = new Login("user123", "senha123");
        Endereco endereco = new Endereco("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(0L, "João Silva", "joao@email.com", "CLIENTE", login, endereco));

        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(-1L, "João Silva", "joao@email.com", "CLIENTE", login, endereco));
    }
}