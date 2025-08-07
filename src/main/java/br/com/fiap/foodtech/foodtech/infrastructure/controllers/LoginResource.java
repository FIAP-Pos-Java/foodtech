package br.com.fiap.foodtech.foodtech.infrastructure.controllers;

import br.com.fiap.foodtech.foodtech.core.controllers.LoginController;
import br.com.fiap.foodtech.foodtech.core.dtos.AlterarSenhaDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.LoginDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.LoginDataDTO;
import br.com.fiap.foodtech.foodtech.infrastructure.data.repositories.DataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@Tag(name = "Login", description = "Controller responsável pela autenticação dos usuários")
public class LoginResource {

    @Autowired
    DataRepository dataRepository;

    @Operation(
            description = "Efetuar autenticação do usuário ao sistema",
            summary = "Autenticar usuário",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401")
            }
    )
    @PostMapping
    public ResponseEntity<String> autenticar(@RequestBody LoginDTO loginDTO){
        LoginController loginController = LoginController.create(dataRepository);
        loginController.autenticar(loginDTO);
        return new ResponseEntity<>("Login efetuado com sucesso!", HttpStatus.OK);
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
    public ResponseEntity<String> updateSenha(@RequestBody AlterarSenhaDTO alterarSenhaDTO){
        LoginController loginController = LoginController.create(dataRepository);
        loginController.alterarSenha(alterarSenhaDTO);
        return new ResponseEntity<>("Senha alterada com sucesso!", HttpStatus.OK);
    }

}
