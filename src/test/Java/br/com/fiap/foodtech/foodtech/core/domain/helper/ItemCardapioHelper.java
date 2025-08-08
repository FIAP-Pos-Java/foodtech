package br.com.fiap.foodtech.foodtech.core.domain.helper;



import br.com.fiap.foodtech.foodtech.core.domain.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.core.dtos.ItemCardapioDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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