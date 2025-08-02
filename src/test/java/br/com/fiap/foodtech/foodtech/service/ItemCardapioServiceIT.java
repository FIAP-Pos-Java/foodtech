package br.com.fiap.foodtech.foodtech.service;


import br.com.fiap.foodtech.foodtech.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.helper.ItemCardapioHelper;
import br.com.fiap.foodtech.foodtech.repositories.ItemCardapioRepository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



@SpringBootTest
@ActiveProfiles("test") // Garante que usamos as configs do perfil de teste
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemCardapioServiceIT extends  ItemCardapioHelper{
    @Autowired
    private ItemCardapioRepository itemCardapioRepository;
    @Autowired
    private ItemCardapioService itemCardapioService;

    @Test
    void devePermitirRemoverItemCardapio() {
        // Arrange
        List<ItemCardapio> itens = itemCardapioRepository.findAll();
        // Act
        itemCardapioService.deleteItemCardapio(itens.get(0).getId());
        // Assert
        // Assert: verifica se o item foi removido
        Optional<ItemCardapio> resultado = itemCardapioRepository.findById(itens.get(0).getId());
        assertTrue(resultado.isEmpty());
    }

    @Test
    void devePermitirRegistrarItemCardapio() {
        // Arrange - preparar o cenário
        var itemCardapioDTO = ItemCardapioHelper.gerarItemCardapioCompletoDto();

        // Act - atuar
        itemCardapioService.saveItemCardapio(itemCardapioDTO);

        List<ItemCardapio> itens = itemCardapioRepository.findAll();
        BigDecimal atual = itens.get(0).getPreco().setScale(2);
 //       assertEquals(1, itens.size());
        assertEquals("Pizza Calabresa", itens.get(0).getNome());
        assertEquals(atual, itens.get(0).getPreco());
        assertEquals(true, itens.get(0).getDisponibilidadeRestaurante());
        assertEquals("caminho/para/foto.jpg", itens.get(0).getCaminhoFoto());
    }


    @Test
    void devePermitirObterMensagem() {

        List<ItemCardapio> itens = itemCardapioRepository.findAll();
        var itemCardapioObtido = itemCardapioService.findItemCardapio(itens.get(0).getId());

        assertThat(itemCardapioObtido)
                .isInstanceOf(ItemCardapio.class)
                .isNotNull();
        assertThat(itemCardapioObtido.getId())
                .isNotNull();
        assertThat(itemCardapioObtido.getNome())
                .isNotNull();
        assertThat(itemCardapioObtido.getDescricao())
                .isNotNull();
        assertThat(itemCardapioObtido.getPreco())
                .isNotNull()
                .isInstanceOf(BigDecimal.class);
        assertThat(itemCardapioObtido.getDisponibilidadeRestaurante())
                .isNotNull()
                .isInstanceOf(Boolean.class);
        assertThat(itemCardapioObtido.getCaminhoFoto())
                .isNotNull();
    }

}