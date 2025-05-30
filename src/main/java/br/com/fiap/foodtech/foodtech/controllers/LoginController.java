package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.LoginDTO;
import br.com.fiap.foodtech.foodtech.service.LoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Log log = LogFactory.getLog(LoginController.class);
    private final LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Void> autenticar(@RequestBody LoginDTO loginDTO){
        logger.info("POST -> /login");
        this.loginService.validarLogin(loginDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateSenha(@RequestBody LoginDTO loginDTO){
        logger.info("PUT -> /login");
        this.loginService.updateSenha(loginDTO);
        return ResponseEntity.noContent().build();
    }

}
