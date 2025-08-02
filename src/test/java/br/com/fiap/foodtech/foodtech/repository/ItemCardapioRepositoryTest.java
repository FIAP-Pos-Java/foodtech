package br.com.fiap.foodtech.foodtech.repository;

import br.com.fiap.foodtech.foodtech.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.helper.ItemCardapioHelper;
import br.com.fiap.foodtech.foodtech.repositories.ItemCardapioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.foodtech.foodtech.helper.ItemCardapioHelper.gerarItemCardapio;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

public class ItemCardapioRepositoryTest {

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirSalvarItemCardapio() {

        //arrange - preparar o cenário
        var itemCardapio = gerarItemCardapio();
        when(itemCardapioRepository.save(any(ItemCardapio.class)))
                .thenReturn(itemCardapio);
        //Act - executar a ação
        var itemCardapioSalvo = itemCardapioRepository.save(itemCardapio);

        //Assert - verificar o resultado
        verify(itemCardapioRepository, times(1)).save(itemCardapio);

 }
    @Test
    void devePermitirConsultarTodosItensCardapio  () {
        // Arrange - preparar o cenário
        var id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        var itemCardapio = ItemCardapioHelper.gerarItemCardapio();
        itemCardapio.setId(id);

        when(itemCardapioRepository.findById(id))
                .thenReturn(Optional.of(itemCardapio));

        // Act - executar a ação
        var itenCardapioSalvo = itemCardapioRepository.findById(id);

        // Assert - verificar o resultado
        assertThat(itenCardapioSalvo)
                .isNotNull()
                .containsSame(itemCardapio);

    }
}
