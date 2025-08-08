package br.com.fiap.foodtech.foodtech.infrastructure.controllers;

import br.com.fiap.foodtech.foodtech.core.controllers.ClienteController;
import br.com.fiap.foodtech.foodtech.core.controllers.RestauranteController;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.infrastructure.data.repositories.DataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
@Tag(name = "Restaurante", description = "Controller responsável pelo gerenciamento dos restaurantes")
public class RestauranteResource {

    @Autowired
    DataRepository dataRepository;

    @Operation(
            description = "Buscar todos os restaurantes paginados",
            summary = "Buscar restaurantes",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<Pagina<RestauranteDTO>> findAllRestaurantes(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        RestauranteController restauranteController = RestauranteController.create(dataRepository);
        Paginacao paginacao = new Paginacao(page, size);
        return new ResponseEntity<>(restauranteController.buscarTodosRestaurantes(paginacao), HttpStatus.OK);
    }

    @Operation(
            description = "Buscar um restaurante por ID",
            summary = "Buscar restaurante",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestauranteDTO> findRestaurante(@PathVariable("id") Long id) {
        RestauranteController restauranteController = RestauranteController.create(dataRepository);
        return new ResponseEntity<>(restauranteController.buscarPorId(id), HttpStatus.OK);
    }

    @Operation(
            description = "Salvar um novo restaurante",
            summary = "Salvar restaurante",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400")
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveRestaurante(@RequestBody NovoRestauranteDTO novoRestauranteDTO) {
        RestauranteController restauranteController = RestauranteController.create(dataRepository);
        restauranteController.cadastrar(novoRestauranteDTO);
        return ResponseEntity.status(201).build();
    }


    @Operation(
            description = "Atualizar um restaurante existente",
            summary = "Atualizar restaurante",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurante(@PathVariable("id") Long id, @RequestBody RestauranteDataDTO restauranteDataDTO) {
        RestauranteController restauranteController = RestauranteController.create(dataRepository);
        restauranteController.atualizar(id, restauranteDataDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(
            description = "Excluir um restaurante por ID",
            summary = "Excluir restaurante",
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable("id") Long id) {
        RestauranteController restauranteController = RestauranteController.create(dataRepository);
        restauranteController.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
