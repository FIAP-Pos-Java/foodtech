package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.ResourceExceptionDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ResourceExceptionDTO> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");

        if (statusCode == null) {
            statusCode = HttpStatus.NOT_FOUND.value();
        }

        String message;
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            message = "Endpoint não encontrado. Endpoints disponíveis: /clientes, /gestores, /login";
        } else {
            message = "Erro ao processar requisição";
        }

        return ResponseEntity.status(statusCode)
                .body(new ResourceExceptionDTO(message, statusCode));
    }
}