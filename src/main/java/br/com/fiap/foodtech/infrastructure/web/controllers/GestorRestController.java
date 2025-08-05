package br.com.fiap.foodtech.infrastructure.web.controllers;

import br.com.fiap.foodtech.core.adapters.controllers.GestorController;
import br.com.fiap.foodtech.core.dto.GestorDTO;
import br.com.fiap.foodtech.core.dto.NovoGestorDTO;
import br.com.fiap.foodtech.core.exceptions.GestorJaExistenteException;
import br.com.fiap.foodtech.core.exceptions.GestorNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/gestores")
public class GestorRestController {

    private final IDataStorageSource dataStorageSource;

    public GestorRestController(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    
    @PostMapping
    public ResponseEntity<?> cadastrarGestor(@Valid @RequestBody NovoGestorDTO novoGestorDTO) {
        try {
            var gestorController = GestorController.create(dataStorageSource);
            GestorDTO gestorCadastrado = gestorController.cadastrar(novoGestorDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(gestorCadastrado);

        } catch (GestorJaExistenteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("Gestor já existe", e.getMessage()));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Dados inválidos", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarGestorPorId(@PathVariable Long id) {
        try {
            var gestorController = GestorController.create(dataStorageSource);
            GestorDTO gestor = gestorController.buscarPorId(id);

            return ResponseEntity.ok(gestor);

        } catch (GestorNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Gestor não encontrado", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    
    private record ErrorResponse(String tipo, String mensagem) {}
}