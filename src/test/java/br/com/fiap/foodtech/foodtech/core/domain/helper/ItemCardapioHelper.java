package br.com.fiap.foodtech.foodtech.core.domain.helper;



import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;

import java.math.BigDecimal;

public abstract class ItemCardapioHelper {

        public static ItemCardapio gerarItemCardapio() {
            return new ItemCardapio(1L,
                    "Piiza Queijo",
                    "Deliciosa pizza de queijo",
                    BigDecimal.valueOf(12.99),
                    true,
                    "caminho/para/foto.jpg");
        }

    }