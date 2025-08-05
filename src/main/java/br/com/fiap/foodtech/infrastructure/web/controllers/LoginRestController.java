package br.com.fiap.foodtech.infrastructure.web.controllers;

import br.com.fiap.foodtech.core.adapters.controllers.ClienteController;
import br.com.fiap.foodtech.core.dto.AlterarSenhaDTO;
import br.com.fiap.foodtech.core.dto.LoginDTO;
import br.com.fiap.foodtech.core.exceptions.CredenciaisInvalidasException;
import br.com.fiap.foodtech.core.exceptions.LoginInvalidoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/login")
public class LoginRestController {

    private final IDataStorageSource dataStorageSource;

    public LoginRestController(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    
    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            var clienteController = ClienteController.create(dataStorageSource);
            boolean autenticado = clienteController.autenticar(loginDTO);

            if (autenticado) {
                return ResponseEntity.ok()
                        .body(new SuccessResponse("Usuário autenticado com sucesso"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Falha na autenticação", "Credenciais inválidas"));
            }

        } catch (LoginInvalidoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Usuário não encontrado", e.getMessage()));

        } catch (CredenciaisInvalidasException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Credenciais inválidas", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    
    @PutMapping("/alterar-senha")
    public ResponseEntity<?> alterarSenha(@Valid @RequestBody AlterarSenhaDTO alterarSenhaDTO) {
        try {
            var clienteController = ClienteController.create(dataStorageSource);
            clienteController.alterarSenha(alterarSenhaDTO);

            return ResponseEntity.ok()
                    .body(new SuccessResponse("Senha alterada com sucesso"));

        } catch (LoginInvalidoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Usuário não encontrado", e.getMessage()));

        } catch (CredenciaisInvalidasException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Credenciais inválidas", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    
    private record ErrorResponse(String tipo, String mensagem) {}
    private record SuccessResponse(String mensagem) {}
}