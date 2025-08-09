package br.com.fiap.foodtech.foodtech.core.domain.helper;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoEnderecoDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoRestauranteDTO;


import java.time.LocalTime;

public abstract class RestauranteHelper {

    public static Restaurante gerarRestaurante() {
        Long id = 1L; // ID fictício para o restaurante
        String nome = "Restaurante Exemplo";
        String tipoCozinha = "Italiana";
        LocalTime abertura = LocalTime.of(11, 0);
        LocalTime fechamento = LocalTime.of(23, 0);
        Endereco enderecoRestaurante = new Endereco(2L, "Rua do Restaurante",
                "456",
                "Bairro do Restaurante",
                "Cidade do Restaurante",
                "Estado do Restaurante",
                "12345-678");
        Endereco enderecoGestor = new Endereco(3L, "Rua do Gestor",
                "456",
                "Bairro do Gestor",
                "Cidade do Gestor",
                "Estado do Gestor",
                "12345-678");
        Gestor gestor = new Gestor(4L, "Nome do Gestor", "emailgestor@email.com",
                "GESTOR",
                enderecoGestor,
                new Login("emailgestor@email.com", "senhaSegura"));

        return new Restaurante(id, nome,
                tipoCozinha,
                abertura,
                fechamento,
                enderecoRestaurante,
                gestor);

    }

    public static NovoRestauranteDTO gerarNovoRestauranteDTO() {
        return new NovoRestauranteDTO(
                "Restaurante Teste",
                "Italiana",
                LocalTime.of(11, 0),
                LocalTime.of(22, 0),
                1L,
                new NovoEnderecoDTO("Rua Exemplo", "123", "Centro", "São Paulo", "SP", "01234-567")
        );
    }

    public static Gestor gerarGestor() {
        Endereco enderecoGestor = new Endereco(1L, "Rua do Gestor",
                "456",
                "Bairro do Gestor",
                "Cidade do Gestor",
                "Estado do Gestor",
                "12345-678");
        return new Gestor(1L, "Nome do Gestor", "emailgestor@email.com",
                "GESTOR",
                enderecoGestor,
                new Login("emailgestor@email.com", "senhaSegura"));
    }
}

