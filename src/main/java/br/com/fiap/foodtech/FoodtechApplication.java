package br.com.fiap.foodtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"br.com.fiap.foodtech.infrastructure",
				"br.com.fiap.foodtech.core"
		}
)
public class FoodtechApplication {
	public static void main(String[] args) {
		SpringApplication.run(FoodtechApplication.class, args);
	}
}
