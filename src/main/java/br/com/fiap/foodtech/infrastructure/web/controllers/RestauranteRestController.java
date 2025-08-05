package br.com.fiap.foodtech.infrastructure.web.controllers;

import br.com.fiap.foodtech.core.adapters.controllers.RestauranteController;
import br.com.fiap.foodtech.core.dto.NovoRestauranteDTO;
import br.com.fiap.foodtech.core.dto.RestauranteDTO;
import br.com.fiap.foodtech.core.exceptions.GestorNaoEncontradoException;
import br.com.fiap.foodtech.core.exceptions.RestauranteNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteRestController {

    private final IDataStorageSource dataStorageSource;

    public RestauranteRestController(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    
    @PostMapping
    public ResponseEntity<?> cadastrarRestaurante(@Valid @RequestBody NovoRestauranteDTO novoRestauranteDTO) {
        try {
            var restauranteController = RestauranteController.create(dataStorageSource);
            RestauranteDTO restauranteCadastrado = restauranteController.cadastrar(novoRestauranteDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteCadastrado);

        } catch (GestorNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Gestor não encontrado", e.getMessage()));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Dados inválidos", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRestaurantePorId(@PathVariable Long id) {
        try {
            var restauranteController = RestauranteController.create(dataStorageSource);
            RestauranteDTO restaurante = restauranteController.buscarPorId(id);

            return ResponseEntity.ok(restaurante);

        } catch (RestauranteNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Restaurante não encontrado", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    
    @GetMapping
    public ResponseEntity<?> listarRestaurantes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            var restauranteController = RestauranteController.create(dataStorageSource);
            List<RestauranteDTO> restaurantes = restauranteController.listarTodos(page, size);

            if (restaurantes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(restaurantes);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    
    private record ErrorResponse(String tipo, String mensagem) {}
}