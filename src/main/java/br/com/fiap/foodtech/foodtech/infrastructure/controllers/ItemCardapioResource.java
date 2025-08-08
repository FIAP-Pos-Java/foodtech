package br.com.fiap.foodtech.foodtech.infrastructure.controllers;

import br.com.fiap.foodtech.foodtech.core.controllers.ItemCardapioController;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.ItemCardapioEntity;
import br.com.fiap.foodtech.foodtech.infrastructure.data.repositories.DataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/itens-do-cardapio")
@Tag(name = "Cardapio", description = "Controller responsável pelo gerenciamento dos itens do cardápio")
public class ItemCardapioResource {

    @Autowired
    DataRepository dataRepository;

    @Operation(
            description = "Buscar todos os itens paginados",
            summary = "Buscar itens do cardápio",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<Pagina<ItemCardapioDTO>> findAlitensCardapio(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        ItemCardapioController itemCardapioController = ItemCardapioController.create(dataRepository);
        Paginacao paginacao = new Paginacao(page, size);
        return new ResponseEntity<>(itemCardapioController.buscarTodosItensCardapio(paginacao), HttpStatus.OK);
    }

    @Operation(
            description = "Buscar um item por ID",
            summary = "Buscar item do cardápio",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemCardapioDTO> findItemCardapio(@PathVariable("id") Long id) {
        ItemCardapioController itemCardapioController = ItemCardapioController.create(dataRepository);
        return new ResponseEntity<>(itemCardapioController.buscarPorId(id), HttpStatus.OK);
    }

    @Operation(
            description = "salvar um novo item no cardápio",
            summary = "Salvar item",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveItemCardapio(@Valid @RequestBody NovoItemCardapioDTO novoItemCardapioDTO) {
        ItemCardapioController itemCardapioController = ItemCardapioController.create(dataRepository);
        itemCardapioController.cadastrar(novoItemCardapioDTO);
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
    public ResponseEntity<Void> updateItemCardapio(@PathVariable("id") Long id, @Valid @RequestBody ItemCardapioDataDTO itemCardapioDataDTO) {
        ItemCardapioController itemCardapioController = ItemCardapioController.create(dataRepository);
        itemCardapioController.atualizar(id, itemCardapioDataDTO);
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
        ItemCardapioController itemCardapioController = ItemCardapioController.create(dataRepository);
        itemCardapioController.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
