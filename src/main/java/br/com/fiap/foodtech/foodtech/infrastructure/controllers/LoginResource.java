package br.com.fiap.foodtech.foodtech.infrastructure.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@Tag(name = "Login", description = "Controller responsável pela autenticação dos usuários")
public class LoginResource {

    private static final Logger logger = LoggerFactory.getLogger(LoginResource.class);

    /*@Operation(
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
    }*/

}
