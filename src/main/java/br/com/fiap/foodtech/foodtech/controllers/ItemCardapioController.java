package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.ItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.service.ItemCardapioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensCardapio")
@Tag(name = "Cardapio", description = "Controller responsável pelo gerenciamento dos itens do cardápio")
public class ItemCardapioController {

    private final ItemCardapioService itemCardapioService;

    private static final Logger logger = LoggerFactory.getLogger(ItemCardapioController.class);

    public ItemCardapioController(ItemCardapioService itemCardapioService) {
        this.itemCardapioService = itemCardapioService;
    }


    @Operation(
            description = "Buscar todos os itens paginados",
            summary = "Buscar itens do cardápio",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<ItemCardapio>> findAlitensCardapio(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("GET /itensCardapio");
        var itensCardapio = this.itemCardapioService.findAllItensCardapio(page, size);
        return new ResponseEntity<>(itensCardapio.getContent(), HttpStatus.OK);
    }

    @Operation(
            description = "Buscar um item por ID",
            summary = "Buscar item do cardápio",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @GetMapping("/{id}")
    public ItemCardapio findItemCardapio(@PathVariable("id") Long id) {
        logger.info("GET /itensCardapio/" + id);
        return this.itemCardapioService.findItemCardapio(id);
    }

    @Operation(
            description = "salvar um novo item no cardápio",
            summary = "Salvar item",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveItemCardapio(@Valid @RequestBody ItemCardapioDTO itemCardapioDTO) {
        logger.info("POST /itensCardapio");
        this.itemCardapioService.saveItemCardapio(itemCardapioDTO);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Atualizar um item com base no ID informado",
            summary = "Atualizar item do cardápio",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItemCardapio(@PathVariable("id") Long id, @Valid @RequestBody ItemCardapioDTO itemCardapioDTO) {
        logger.info("PUT /itensCardapio/" + id);
        this.itemCardapioService.updateItemCardapio(id, itemCardapioDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(
            description = "Excluir um item com base no ID informado",
            summary = "Deletar item do cardápio",
            responses = {
                @ApiResponse(description = "NoContent", responseCode = "204"),
                @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemCardapio(@PathVariable("id") Long id) {
        logger.info("DELETE /itensCardapio/" + id);
        this.itemCardapioService.deleteItemCardapio(id);
        return ResponseEntity.noContent().build();
    }

}
