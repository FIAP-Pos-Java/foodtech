package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.ResourceExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<ResourceExceptionDTO> home() {
        var message = "Bem-vindo à API FoodTech. Por favor, use um endpoint válido. " +
                "Endpoints disponíveis: /clientes, /gestores, /login";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResourceExceptionDTO(message, HttpStatus.BAD_REQUEST.value()));
    }
}