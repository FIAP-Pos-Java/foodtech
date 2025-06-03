package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.LoginDTO;
import br.com.fiap.foodtech.foodtech.dto.UpdateSenhaDTO;
import br.com.fiap.foodtech.foodtech.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Tag(name = "Login", description = "Controller responsável pela autenticação dos usuários")
public class LoginController {

    private static final Log log = LogFactory.getLog(LoginController.class);

    private final LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(
            description = "Efetuar autenticação do usuário ao sistema",
            summary = "Autenticar usuário",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401")
            }
    )
    @PostMapping
    public ResponseEntity<Void> autenticar(@RequestBody LoginDTO loginDTO){
        logger.info("POST -> /login");
        this.loginService.validarLogin(loginDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(
            description = "Alterar senha do usuário",
            summary = "Alterar senha do usuário",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401")
            }
    )
    @PutMapping
    public ResponseEntity<Void> updateSenha(@RequestBody UpdateSenhaDTO updateSenhaDTO){
        logger.info("PUT -> /login");
        this.loginService.updateSenha(updateSenhaDTO);
        return ResponseEntity.noContent().build();
    }

}
