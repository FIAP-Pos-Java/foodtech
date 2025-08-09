package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoEnderecoDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoGestorDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoLoginDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorJaExistenteException;
import br.com.fiap.foodtech.foodtech.core.gateways.IGestorGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastrarGestorUseCaseTest {

    private IGestorGateway gestorGateway;
    private CadastrarGestorUseCase cadastrarGestorUseCase;

    @BeforeEach
    void setUp() {
        gestorGateway = mock(IGestorGateway.class);
        cadastrarGestorUseCase = CadastrarGestorUseCase.create(gestorGateway);
    }

    @Test
    void deveCadastrarGestorComSucesso() throws Exception {
        NovoGestorDTO dto = new NovoGestorDTO(
                "Carlos Andrade",
                "carlos@empresa.com",
                "GESTOR",
                new NovoLoginDTO("carlos@email.com.br", "senhaSegura"),
                new NovoEnderecoDTO("Av. Paulista", "1000", "Bela Vista", "São Paulo", "SP", "01310-100")
        );

        when(gestorGateway.buscarPorEmail(dto.email())).thenReturn(null);

        Gestor gestorEsperado = new Gestor(
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                new Endereco(dto.endereco().logradouro(), dto.endereco().numero(), dto.endereco().bairro(), dto.endereco().cidade(),
                        dto.endereco().estado(), dto.endereco().cep()),
                new Login(dto.login().login(), dto.login().senha())
        );

        when(gestorGateway.incluir(any(Gestor.class))).thenReturn(gestorEsperado);

        Gestor resultado = cadastrarGestorUseCase.run(dto);

        assertNotNull(resultado);
        assertEquals(dto.email(), resultado.getEmail());
        verify(gestorGateway).incluir(any(Gestor.class));
    }

    @Test
    void deveLancarExcecaoQuandoGestorJaExiste() {
        NovoGestorDTO dto = new NovoGestorDTO(
                "Carlos Andrade",
                "carlos@empresa.com",
                "GESTOR",
                new NovoLoginDTO("carlos@email.com.br", "senhaSegura"),
                new NovoEnderecoDTO("Av. Paulista", "1000", "Bela Vista", "São Paulo", "SP", "01310-100")
        );

        Gestor gestorExistente = new Gestor(
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                new Endereco(dto.endereco().logradouro(), dto.endereco().numero(), dto.endereco().bairro(), dto.endereco().cidade(),
                        dto.endereco().estado(), dto.endereco().cep()),
                new Login(dto.login().login(), dto.login().senha())
        );

        when(gestorGateway.buscarPorEmail(dto.email())).thenReturn(gestorExistente);

        assertThrows(GestorJaExistenteException.class, () -> cadastrarGestorUseCase.run(dto));
    }
}
