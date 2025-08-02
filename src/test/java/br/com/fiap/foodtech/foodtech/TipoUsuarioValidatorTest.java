package br.com.fiap.foodtech.foodtech;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Endereco;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.entities.Login;
import br.com.fiap.foodtech.foodtech.validation.TipoUsuarioValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class TipoUsuarioValidatorTest {

    @Autowired
    private TipoUsuarioValidator tipoUsuarioValidator;

    private Cliente cliente = null;
    private Gestor gestor = null;
    private Login login = null;
    private Endereco endereco = null;

    @BeforeEach
    void setUp() {
        login = new Login("deckard", "0802");
        endereco = new Endereco("rua nova", "688", "pedreira", "belém", "Pará", "66083-442");
        cliente = new Cliente("elias", "elias@gmail.com", "cliente", login, endereco);
        gestor = new Gestor("elias", "elias@gmail.com", "dono de restaurante", login, endereco);
    }

    @AfterEach
    void tearDown() {
        login = null;
        endereco = null;
        cliente = null;
        gestor = null;
    }

    @Test
    @DisplayName("validando o tipoUsuario quando passamos uma entidade do tipo cliente")
    void deveValidarTipoUsuarioComCliente() {

        String resultado = tipoUsuarioValidator.validandoTipoUsuario(cliente);

        assertThat(resultado).isEqualTo("CLIENTE");
    }

    @Test
    @DisplayName("validando o tipoUsuario quando passamos uma entidade do Gestor")
    void deveValidarTipoUsuarioComGestor() {

        String resultado = tipoUsuarioValidator.validandoTipoUsuario(gestor);

        assertThat(resultado).isEqualTo("DONODERESTAURANTE");
    }

    @Test
    @DisplayName("deve gerar uma DataIntegrityViolationException quando tentamos passar um dono de restaurante para o tipoUsuario que deveria receber cliente")
    void naoDeveValidarTipoUsuarioComUmGestor() {
        cliente.setTipoUsuario("dono de restaurante");

        assertThatThrownBy(() -> tipoUsuarioValidator.validandoTipoUsuario(cliente))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessage("esse tipo de usuário não pode ser salvo em Cliente");
    }

    @Test
    @DisplayName("deve gerar uma DataIntegrityViolationException quando tentamos passar um cliente para o tipoUsuario que deveria receber dono de restaurante")
    void naoDeveValidarTipoUsuarioComUmCliente() {
        gestor.setTipoUsuario("cliente");

        assertThatThrownBy(() -> tipoUsuarioValidator.validandoTipoUsuario(gestor))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessage("esse tipo de usuário não pode ser salvo em Gestor");
    }
}
