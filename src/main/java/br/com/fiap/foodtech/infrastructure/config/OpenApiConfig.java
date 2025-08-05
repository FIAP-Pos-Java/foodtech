package br.com.fiap.foodtech.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI foodtechOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FoodTech API - Clean Architecture")
                        .description("""
                                API para gerenciamento de restaurantes desenvolvida seguindo os princípios da Clean Architecture.
                                
                                ## Funcionalidades Principais:
                                - **Gestão de Clientes**: Cadastro, consulta e autenticação
                                - **Gestão de Gestores**: Cadastro e consulta de donos de restaurante
                                - **Gestão de Restaurantes**: Cadastro, consulta e listagem de estabelecimentos
                                - **Gestão de Cardápio**: Cadastro, consulta e listagem de itens do cardápio
                                - **Autenticação**: Login e alteração de senhas
                                """));
    }
}