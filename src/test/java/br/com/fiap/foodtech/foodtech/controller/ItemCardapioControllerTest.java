package br.com.fiap.foodtech.foodtech.controller;

import br.com.fiap.foodtech.foodtech.controllers.handlers.ControllerExceptionHandler;
import br.com.fiap.foodtech.foodtech.controllers.ItemCardapioController;
import br.com.fiap.foodtech.foodtech.dto.ItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.helper.ItemCardapioHelper;
import br.com.fiap.foodtech.foodtech.service.ItemCardapioService;

import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ItemCardapioControllerTest extends ItemCardapioHelper {

    private MockMvc mockMvc;

    @Mock
    private ItemCardapioService itemCardapioService;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        ItemCardapioController itemCardapioController = new ItemCardapioController(itemCardapioService);
        mockMvc = MockMvcBuilders.standaloneSetup(itemCardapioController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }


    @SneakyThrows
    @Test
    void devePermitirRegistrarItemCardapio() {

        // Arrange
        var itemCardapioRequest = gerarItemCardapioCompletoDto();
        doNothing().when(itemCardapioService).saveItemCardapio(any(ItemCardapioDTO.class));

        // Act + Assert
        mockMvc.perform(post("/itensCardapio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(itemCardapioRequest)))
                .andExpect(status().isCreated());
        verify(itemCardapioService, times(1)).saveItemCardapio(any(ItemCardapioDTO.class));
    }

    @Test
    void deveGerarExcecao_QuandoRegistrarItem_NomeEmBraco() throws Exception {
        var itemCardapioRequest = gerarItemCardapioDto();

        mockMvc.perform(post("/itensCardapio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(itemCardapioRequest)))
                .andExpect(status().isBadRequest());
        verify(itemCardapioService, never())
                .saveItemCardapio(any(ItemCardapioDTO.class));
    }

    @SneakyThrows
    @Test
    public  void devePermitirObterMensagem(){
        // Arrange
        Long id = 12L;
        var itemCardapioResponse = gerarItemCardapioCompleto();
        itemCardapioResponse.setId(id);
        when(itemCardapioService.findItemCardapio(any(Long.class)))
                .thenReturn(itemCardapioResponse);

        // Act + Assert
        mockMvc.perform(get("/itensCardapio/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(itemCardapioService, times(1)).findItemCardapio(any(Long.class));
    }
    @SneakyThrows
    @Test
    void deveGerarExcecaoAoObterCardapioComIdNaoExistente()  {
        // Arrange
        Long itemId = 1L;
        when(itemCardapioService.findItemCardapio(itemId))
                .thenThrow(new ResourceNotFoundException("Item not found"));

        mockMvc.perform(get("/itensCardapio/{id}", itemId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Item not found"))
                .andExpect(jsonPath("$.status").value(404));
        verify(itemCardapioService, times(1)).findItemCardapio(any(Long.class));

    }

        @Test
        void devePermitirRemoverItemCardapio() throws Exception {
            Long itemId = 1L;

            // Simula sucesso na exclusão
            doNothing().when(itemCardapioService).deleteItemCardapio(itemId);

            mockMvc.perform(delete("/itensCardapio/{id}", itemId))
                    .andExpect(status().isNoContent()) // Verifica o status 204
                    .andExpect(content().string(""));  // Verifica que o corpo está vazio
            verify(itemCardapioService, times(1))
                    .deleteItemCardapio(any(Long.class));
        }
        @Test
        void devePermitirAlterarItemCardapio() throws Exception {
            Long itemId = 1L;
            var itemCardapioRequest = gerarItemCardapioCompletoDto();
            doNothing().when(itemCardapioService).updateItemCardapio(any(Long.class), any(ItemCardapioDTO.class));

            mockMvc.perform(put("/itensCardapio/{id}", itemId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(itemCardapioRequest)))
                    .andExpect(status().isOk());
            verify(itemCardapioService, times(1))
                    .updateItemCardapio(any(Long.class), any(ItemCardapioDTO.class));
        }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


