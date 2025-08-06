package br.com.fiap.foodtech.core.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    @DisplayName("Deve criar endereço válido")
    void deveCriarEnderecoValido() {

        Endereco endereco = Endereco.create("Rua das Flores", "123", "Centro", "São Paulo", "SP", "01234567");

        assertNotNull(endereco);
        assertEquals("Rua das Flores", endereco.getLogradouro());
        assertEquals("123", endereco.getNumero());
        assertEquals("Centro", endereco.getBairro());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getEstado());
        assertEquals("01234567", endereco.getCep());
    }

    @Test
    @DisplayName("Deve criar endereço com ID específico")
    void deveCriarEnderecoComId() {

        Endereco endereco = Endereco.create(1L, "Rua das Flores", "123", "Centro", "São Paulo", "SP", "01234567");

        assertEquals(1L, endereco.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção para número nulo")
    void deveLancarExcecaoParaNumeroNulo() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.create("Rua das Flores", null, "Centro", "São Paulo", "SP", "01234567"));
    }

    @Test
    @DisplayName("Deve lançar exceção para número vazio")
    void deveLancarExcecaoParaNumeroVazio() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.create("Rua das Flores", "", "Centro", "São Paulo", "SP", "01234567"));
    }

    @Test
    @DisplayName("Deve lançar exceção para cidade nula")
    void deveLancarExcecaoParaCidadeNula() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.create("Rua das Flores", "123", "Centro", null, "SP", "01234567"));
    }

    @Test
    @DisplayName("Deve lançar exceção para estado nulo")
    void deveLancarExcecaoParaEstadoNulo() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.create("Rua das Flores", "123", "Centro", "São Paulo", null, "01234567"));
    }

    @Test
    @DisplayName("Deve lançar exceção para CEP nulo")
    void deveLancarExcecaoParaCepNulo() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.create("Rua das Flores", "123", "Centro", "São Paulo", "SP", null));
    }

    @Test
    @DisplayName("Deve lançar exceção para CEP inválido")
    void deveLancarExcecaoParaCepInvalido() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.create("Rua das Flores", "123", "Centro", "São Paulo", "SP", "1234"));

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.create("Rua das Flores", "123", "Centro", "São Paulo", "SP", "123456789"));
    }

    @Test
    @DisplayName("Deve aceitar CEP com formatação")
    void deveAceitarCepComFormatacao() {

        assertDoesNotThrow(() ->
                Endereco.create("Rua das Flores", "123", "Centro", "São Paulo", "SP", "01234-567"));
    }

    @Test
    @DisplayName("Deve aceitar logradouro e bairro nulos")
    void deveAceitarLogradouroEBairroNulos() {

        assertDoesNotThrow(() ->
                Endereco.create(null, "123", null, "São Paulo", "SP", "01234567"));
    }

    @Test
    @DisplayName("Deve lançar exceção para ID inválido")
    void deveLancarExcecaoParaIdInvalido() {

        assertThrows(IllegalArgumentException.class, () ->
                Endereco.create(0L, "Rua das Flores", "123", "Centro", "São Paulo", "SP", "01234567"));
    }
}