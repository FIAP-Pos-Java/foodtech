package br.com.fiap.foodtech.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.fiap.foodtech.infrastructure.persistence.repositories")
@EnableTransactionManagement
public class ApplicationConfig {
}