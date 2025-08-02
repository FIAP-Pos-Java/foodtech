package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.dto.ItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.helper.ItemCardapioHelper;
import br.com.fiap.foodtech.foodtech.repositories.ItemCardapioRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

public class ItemCardapioServiceTest extends ItemCardapioHelper{

    @Mock
    @MockitoBean
    private ItemCardapioRepository itemCardapioRepository;
    private ItemCardapioService itemCardapioService;
    private Validator validator;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        itemCardapioService = new ItemCardapioService(itemCardapioRepository);
    // Configuração do validador{
        mock = MockitoAnnotations.openMocks(this);
        itemCardapioService = new ItemCardapioService(itemCardapioRepository);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirRegistrarItemCardapio() {

        //arrange - preparar o cenário
        var itemCardapioDTO = ItemCardapioHelper.gerarItemCardapioCompletoDto();
        when(itemCardapioRepository.save(any(ItemCardapio.class)))
                .thenAnswer(i-> i.getArgument(0));

        //Act - executar a ação
        itemCardapioService.saveItemCardapio(itemCardapioDTO);

        //Assert - verificar o resultado
        verify(itemCardapioRepository, times(1)).save(argThat(item ->
                item.getNome().equals(itemCardapioDTO.nome()) &&
                        item.getDescricao().equals(itemCardapioDTO.descricao()) &&
                        item.getPreco().compareTo(itemCardapioDTO.preco()) == 0 &&
                        item.getDisponibilidadeRestaurante().equals(itemCardapioDTO.disponibilidadeRestaurante()) &&
                        item.getCaminhoFoto().equals(itemCardapioDTO.caminhoFoto())
        ));

 }

    @Test
    void deveLancarExcecaoQuandoNomeNaoPreenchido() {
        // Arrange
        var itemCardapioDTO = ItemCardapioHelper.gerarItemCardapioDto(); // crie esse helper para retornar DTO com nome nulo

        // Act
        Set<ConstraintViolation<ItemCardapioDTO>> violations = validator.validate(itemCardapioDTO);

        // Assert
        assertThat(violations)
                .anyMatch(v -> v.getPropertyPath().toString().equals("nome")
                        && v.getMessage().equals("É necessário informar um nome para o prato"));
    }


    @Test
    void devePermitirConsultarTodosItensCardapio  () {
        // Arrange - preparar o cenário
        var id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        var itemCardapio = ItemCardapioHelper.gerarItemCardapioCompleto();

        Page<ItemCardapio> page = new PageImpl<>(Arrays.asList(
                gerarItemCardapioCompleto(),
                gerarItemCardapioCompleto()
        ));

        when(itemCardapioRepository.findAll(any(Pageable.class)))
                    .thenReturn(page);

        when(itemCardapioRepository.findById(id))
                .thenReturn(Optional.of(itemCardapio));

        // Act - executar a ação

        var itenCardapioSalvo = itemCardapioRepository.findById(id);
        Page<ItemCardapio> itensCardapio = itemCardapioService.findAllItensCardapio(1, 10);

        // Assert - verificar o resultado
        assertThat(itenCardapioSalvo)
                .isNotNull()
                .containsSame(itemCardapio);

        assertThat(itensCardapio.getContent())
                .isNotEmpty()
                .hasSize(2)
                .asList()
                .allSatisfy(mensagem -> {
                    assertThat(mensagem).isNotNull();
                    assertThat(mensagem).isInstanceOf(ItemCardapio.class);
                });

    }

    @Test
    void deveGerarExcecao_QuandoAtualizarMensagem_IdNaoCoincide() {
        Long id = 2L;
        BigDecimal valor = new BigDecimal("87.90");

        ItemCardapioDTO itemCardapioDTO = new ItemCardapioDTO(
                "Pizza Margherita",
                "Deliciosa pizza de tomate, manjericão e mussarela",
                valor,
                true,
                "https://example.com/pizza-margherita.jpg");


        ResourceNotFoundException excecao = assertThrows(
                ResourceNotFoundException.class,
                () -> itemCardapioService.updateItemCardapio(id, itemCardapioDTO)
        );

        assertEquals("Item do cardapio não encontrado. ID: " + id, excecao.getMessage());
    }

    @Test
    void devePermirirAtualizarItemCardapio() {
        // Arrange - preparar o cenário
        Long id = 1L;
        var itemCardapioAtualizadoDTO= ItemCardapioHelper.gerarItemCardapioCompletoDto();
        var itemCardapioExistente = ItemCardapioHelper.gerarItemCardapioCompleto();


        when(itemCardapioRepository.findById(id))
                .thenReturn(Optional.of(itemCardapioExistente));

        when(itemCardapioRepository.save(any(ItemCardapio.class)))
                .thenAnswer(i -> i.getArgument(0));

        // Act - executar a ação
         itemCardapioService.updateItemCardapio(id, itemCardapioAtualizadoDTO);

        // Assert - verificar o resultado
        verify(itemCardapioRepository, times(1)).findById(id);
        verify(itemCardapioRepository, times(1)).save(argThat(item ->
                item.getId().equals(id) &&
                        item.getNome().equals(itemCardapioAtualizadoDTO.nome()) &&
                        item.getDescricao().equals(itemCardapioAtualizadoDTO.descricao()) &&
                        item.getPreco().compareTo(itemCardapioAtualizadoDTO.preco()) == 0 &&
                        item.getDisponibilidadeRestaurante().equals(itemCardapioAtualizadoDTO.disponibilidadeRestaurante()) &&
                        item.getCaminhoFoto().equals(itemCardapioAtualizadoDTO.caminhoFoto())
        ));
        verify(itemCardapioRepository, times(1)).save(any(ItemCardapio.class));
    }
}
