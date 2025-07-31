package br.com.fiap.foodtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 *
 * CONCEITO PEDAGÓGICO - O que está acontecendo aqui?
 *
 * Imagine que você está montando uma fábrica complexa:
 * 1. Você tem várias máquinas especializadas (seus componentes)
 * 2. Cada máquina precisa de energia e matéria-prima (dependências)
 * 3. Algumas máquinas dependem de outras para funcionar
 * 4. Alguém precisa ligar tudo na ordem correta
 *
 * O @SpringBootApplication é como um "engenheiro chefe" que:
 * - Liga todas as máquinas (@ComponentScan)
 * - Configura as conexões entre elas (@EnableAutoConfiguration)
 * - Define as regras da fábrica (@Configuration)
 *
 * Quando você chama SpringApplication.run(), é como apertar o "botão principal"
 * que inicia toda a fábrica de forma coordenada.
 */
@SpringBootApplication(
		// Dizemos ao Spring onde procurar por componentes (@Component, @Service, @Repository)
		scanBasePackages = {
				"br.com.fiap.foodtech.infrastructure", // Onde estão nossas implementações concretas
				"br.com.fiap.foodtech.core"           // Onde estão nossos DTOs e interfaces
		}
)
public class FoodtechApplication {

	public static void main(String[] args) {
		/*
		 * SpringApplication.run() é o "big bang" da nossa aplicação.
		 *
		 * Aqui acontece toda a "magia" do Spring Boot:
		 *
		 * 1. ESCANEAMENTO: O Spring varre todos os pacotes especificados
		 *    procurando por classes anotadas (@Component, @Repository, etc.)
		 *
		 * 2. INSTANCIAÇÃO: Cria instâncias de todos os componentes encontrados
		 *    (MySQLDataStorageSource, ClienteJpaRepository, etc.)
		 *
		 * 3. INJEÇÃO DE DEPENDÊNCIAS: Conecta os componentes entre si
		 *    (injeta ClienteJpaRepository dentro de MySQLDataStorageSource)
		 *
		 * 4. CONFIGURAÇÃO: Aplica todas as configurações (@Configuration)
		 *    (configura banco de dados, transações, etc.)
		 *
		 * 5. INICIALIZAÇÃO: Inicia o servidor web na porta 8080
		 *    e deixa a aplicação pronta para receber requisições HTTP
		 *
		 * É como se o Spring fosse um "assistente super inteligente" que:
		 * - Lê suas instruções (anotações)
		 * - Monta tudo automaticamente
		 * - Deixa tudo funcionando
		 */
		SpringApplication.run(FoodtechApplication.class, args);

		/*
		 * DICA PEDAGÓGICA - O que você pode adicionar aqui?
		 *
		 * Se quiser ver o que está acontecendo nos bastidores, pode adicionar:
		 *
		 * System.out.println("=== APLICAÇÃO INICIADA COM SUCESSO ===");
		 * System.out.println("Acesse: http://localhost:8080/api/clientes");
		 * System.out.println("Swagger UI: http://localhost:8080/swagger-ui.html");
		 *
		 * Isso te dará feedback visual de que tudo está funcionando.
		 */
	}
}

/*
 * CONCEITO AVANÇADO - Por que esta estrutura é poderosa?
 *
 * Com esta configuração, você consegue o seguinte "superpoder":
 *
 * 1. MODULARIDADE: Cada camada pode ser modificada independentemente
 *    - Quer trocar MySQL por PostgreSQL? Mude apenas a infrastructure
 *    - Quer adicionar autenticação? Mude apenas os controllers REST
 *    - Quer alterar regras de negócio? Mude apenas o core
 *
 * 2. TESTABILIDADE: Pode testar cada camada isoladamente
 *    - Teste os casos de uso sem banco de dados
 *    - Teste os repositórios sem HTTP
 *    - Teste os controllers sem lógica de negócio
 *
 * 3. FLEXIBILIDADE: Pode reutilizar o core em diferentes contextos
 *    - Aplicação web, mobile, linha de comando
 *    - Diferentes bancos de dados
 *    - Diferentes frameworks
 *
 * É como ter construído um motor (core) que pode ser colocado
 * em diferentes carros (infrastructures)!
 */