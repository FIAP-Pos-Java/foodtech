package br.com.fiap.foodtech.foodtech.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI foodtech() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Food Tech API")
                                .description("API desenvolvida para gerenciamento de restaurantes.")
                                .version("v0.0.1")
                                .license(new License().name("Apache 2.0"))
                );
    }
}
