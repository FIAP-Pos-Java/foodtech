package br.com.fiap.foodtech.foodtech.infrastructure.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/itens-do-cardapio")
@Tag(name = "Cardapio", description = "Controller responsável pelo gerenciamento dos itens do cardápio")
public class ItemCardapioResource {

    private static final Logger logger = LoggerFactory.getLogger(ItemCardapioResource.class);


    /*@Operation(
            description = "Buscar todos os itens paginados",
            summary = "Buscar itens do cardápio",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<ItemCardapioEntity>> findAlitensCardapio(
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
    public ItemCardapioEntity findItemCardapio(@PathVariable("id") Long id) {
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
    }*/

}
