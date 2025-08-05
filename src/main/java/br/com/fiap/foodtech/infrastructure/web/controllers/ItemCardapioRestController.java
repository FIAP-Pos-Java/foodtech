package br.com.fiap.foodtech.infrastructure.web.controllers;

import br.com.fiap.foodtech.core.adapters.controllers.ItemCardapioController;
import br.com.fiap.foodtech.core.dto.ItemCardapioDTO;
import br.com.fiap.foodtech.core.dto.NovoItemCardapioDTO;
import br.com.fiap.foodtech.core.exceptions.ItemCardapioNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/api/itens-cardapio")
public class ItemCardapioRestController {

    private final IDataStorageSource dataStorageSource;

    public ItemCardapioRestController(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    
    @PostMapping
    public ResponseEntity<?> cadastrarItemCardapio(@Valid @RequestBody NovoItemCardapioDTO novoItemCardapioDTO) {
        try {
            var itemCardapioController = ItemCardapioController.create(dataStorageSource);
            ItemCardapioDTO itemCadastrado = itemCardapioController.cadastrar(novoItemCardapioDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(itemCadastrado);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Dados inválidos", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarItemCardapioPorId(@PathVariable Long id) {
        try {
            var itemCardapioController = ItemCardapioController.create(dataStorageSource);
            ItemCardapioDTO item = itemCardapioController.buscarPorId(id);

            return ResponseEntity.ok(item);

        } catch (ItemCardapioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Item não encontrado", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    
    @GetMapping
    public ResponseEntity<?> listarItensCardapio(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            var itemCardapioController = ItemCardapioController.create(dataStorageSource);
            List<ItemCardapioDTO> itens = itemCardapioController.listarTodos(page, size);

            if (itens.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(itens);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    
    private record ErrorResponse(String tipo, String mensagem) {}
}