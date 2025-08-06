package br.com.fiap.foodtech.core.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {
    private Gestor criarGestorValido() {
        Login login = Login.create("gestor123", "senha123");
        Endereco endereco = Endereco.create("Rua A", "123", "Centro", "São Paulo", "SP", "01234567");
        return Gestor.create("Maria Silva", "maria@email.com", login, endereco);
    }

    private Endereco criarEnderecoValido() {
        return Endereco.create("Rua do Restaurante", "456", "Centro", "São Paulo", "SP", "01234567");
    }

    @Test
    @DisplayName("Deve criar restaurante válido")
    void deveCriarRestauranteValido() {

        Gestor gestor = criarGestorValido();
        Endereco endereco = criarEnderecoValido();
        LocalTime abertura = LocalTime.of(18, 0);
        LocalTime fechamento = LocalTime.of(23, 0);

        Restaurante restaurante = Restaurante.create(
                "Pizzaria do João", "Italiana", abertura, fechamento, gestor, endereco);

        assertNotNull(restaurante);
        assertEquals("Pizzaria do João", restaurante.getNome());
        assertEquals("Italiana", restaurante.getTipoCozinha());
        assertEquals(abertura, restaurante.getHorarioAbertura());
        assertEquals(fechamento, restaurante.getHorarioFechamento());
        assertEquals(gestor, restaurante.getGestor());
        assertEquals(endereco, restaurante.getEndereco());
    }

    @Test
    @DisplayName("Deve criar restaurante com ID específico")
    void deveCriarRestauranteComId() {

        Gestor gestor = criarGestorValido();
        Endereco endereco = criarEnderecoValido();
        LocalTime abertura = LocalTime.of(18, 0);
        LocalTime fechamento = LocalTime.of(23, 0);

        Restaurante restaurante = Restaurante.create(
                1L, "Pizzaria do João", "Italiana", abertura, fechamento, gestor, endereco);

        assertEquals(1L, restaurante.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção para nome nulo")
    void deveLancarExcecaoParaNomeNulo() {

        Gestor gestor = criarGestorValido();
        Endereco endereco = criarEnderecoValido();

        assertThrows(IllegalArgumentException.class, () ->
                Restaurante.create(null, "Italiana", LocalTime.of(18, 0), LocalTime.of(23, 0), gestor, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para nome muito curto")
    void deveLancarExcecaoParaNomeMuitoCurto() {

        Gestor gestor = criarGestorValido();
        Endereco endereco = criarEnderecoValido();

        assertThrows(IllegalArgumentException.class, () ->
                Restaurante.create("A", "Italiana", LocalTime.of(18, 0), LocalTime.of(23, 0), gestor, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para tipo de cozinha nulo")
    void deveLancarExcecaoParaTipoCozinhaNulo() {

        Gestor gestor = criarGestorValido();
        Endereco endereco = criarEnderecoValido();

        assertThrows(IllegalArgumentException.class, () ->
                Restaurante.create("Pizzaria", null, LocalTime.of(18, 0), LocalTime.of(23, 0), gestor, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para horário de abertura nulo")
    void deveLancarExcecaoParaHorarioAberturaNulo() {

        Gestor gestor = criarGestorValido();
        Endereco endereco = criarEnderecoValido();

        assertThrows(IllegalArgumentException.class, () ->
                Restaurante.create("Pizzaria", "Italiana", null, LocalTime.of(23, 0), gestor, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para horário de fechamento nulo")
    void deveLancarExcecaoParaHorarioFechamentoNulo() {

        Gestor gestor = criarGestorValido();
        Endereco endereco = criarEnderecoValido();

        assertThrows(IllegalArgumentException.class, () ->
                Restaurante.create("Pizzaria", "Italiana", LocalTime.of(18, 0), null, gestor, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para gestor nulo")
    void deveLancarExcecaoParaGestorNulo() {

        Endereco endereco = criarEnderecoValido();

        assertThrows(IllegalArgumentException.class, () ->
                Restaurante.create("Pizzaria", "Italiana", LocalTime.of(18, 0), LocalTime.of(23, 0), null, endereco));
    }

    @Test
    @DisplayName("Deve lançar exceção para endereco nulo")
    void deveLancarExcecaoParaEnderecoNulo() {

        Gestor gestor = criarGestorValido();

        assertThrows(IllegalArgumentException.class, () ->
                Restaurante.create("Pizzaria", "Italiana", LocalTime.of(18, 0), LocalTime.of(23, 0), gestor, null));
    }

    @Test
    @DisplayName("Deve permitir restaurante 24h")
    void devePermitirRestaurante24h() {

        Gestor gestor = criarGestorValido();
        Endereco endereco = criarEnderecoValido();
        LocalTime abertura = LocalTime.of(22, 0);
        LocalTime fechamento = LocalTime.of(2, 0);

        assertDoesNotThrow(() ->
                Restaurante.create("Lanchonete 24h", "Fast Food", abertura, fechamento, gestor, endereco));
    }
}