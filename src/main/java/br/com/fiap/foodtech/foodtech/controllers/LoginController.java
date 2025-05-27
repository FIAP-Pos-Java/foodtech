package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.LoginDTO;
import br.com.fiap.foodtech.foodtech.dto.SenhaDTO;
import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.service.LoginClienteService;
import br.com.fiap.foodtech.foodtech.service.LoginGestorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginClienteService loginClienteService;
    private final LoginGestorService loginGestorService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(LoginClienteService loginClienteService, LoginGestorService loginGestorService) {
        this.loginClienteService = loginClienteService;
        this.loginGestorService = loginGestorService;
    }

    @PostMapping("/cliente")
    public ResponseEntity<Optional<Cliente>> validarLoginGestor(
            @Valid
            @RequestBody LoginDTO loginDTO) {
        logger.info("POST /cliente/login");
        loginClienteService.validarLogin(loginDTO);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/gestor")
    public ResponseEntity<Optional<Gestor>> validarLoginCliente(
            @Valid
            @RequestBody LoginDTO loginDTO) {
        logger.info("POST /gestor/login");
        loginGestorService.validarLogin(loginDTO);
        return ResponseEntity.status(201).build();
    }


    @PutMapping("/cliente/senha")
    public ResponseEntity<Void> UpdateClienteSenha(
            @Valid
            @RequestBody SenhaDTO senha) {
        logger.info("GET /login/senha/cliente" + senha.novaSenha());
        loginClienteService.UpdateSenha(senha);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/gestor/senha")
    public ResponseEntity<Void> UpdateGestorSenha(
            @Valid
            @RequestBody SenhaDTO senha) {
        logger.info("GET /login/senha/gestor" + senha.novaSenha());
        loginGestorService.UpdateSenha(senha);
        return ResponseEntity.ok().build();
    }
}
