package br.com.fiap.foodtech.foodtech.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCardapioDTOValidationTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveDetectarCamposInvalidosNoDTO() {
        ItemCardapioDTO dto = ItemCardapioDTO.builder()
                .nome("") // inválido
                .descricao("") // inválido
                .preco(null) // inválido
                .disponibilidadeRestaurante(null) // inválido
                .caminhoFoto("") // inválido
                .build();

        Set<ConstraintViolation<ItemCardapioDTO>> violacoes = validator.validate(dto);

        assertEquals(5, violacoes.size());
        violacoes.forEach(v -> System.out.println(v.getPropertyPath() + ": " + v.getMessage()));
    }

    @Test
    void devePassarComCamposValidosNoDTO() {
        ItemCardapioDTO dto = ItemCardapioDTO.builder()
                .nome("Risoto de Cogumelos")
                .descricao("Risoto cremoso com cogumelos frescos")
                .preco(new BigDecimal("45.00"))
                .disponibilidadeRestaurante(true)
                .caminhoFoto("risoto.jpg")
                .build();

        Set<ConstraintViolation<ItemCardapioDTO>> violacoes = validator.validate(dto);

        assertTrue(violacoes.isEmpty());
    }
}
