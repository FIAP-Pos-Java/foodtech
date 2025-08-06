package br.com.fiap.foodtech.foodtech.infrastructure.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurantes")
@Tag(name = "Restaurante", description = "Controller responsável pelo gerenciamento dos restaurantes")
public class RestauranteResource {

    private static final Logger logger = LoggerFactory.getLogger(RestauranteResource.class);

    /*@Operation(
            description = "Buscar todos os restaurantes paginados",
            summary = "Buscar restaurantes",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> findAllRestaurantes(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("GET /restaurantes");
        var restaurantes = this.restauranteService.findAllRestaurantes(page, size);

        if (restaurantes.isEmpty())
            return ResponseEntity.noContent().build();

        return new ResponseEntity<>(restaurantes.getContent(), HttpStatus.OK);
    }

    @Operation(
            description = "Buscar um restaurante por ID",
            summary = "Buscar restaurante",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> findRestaurante(@PathVariable("id") Long id) {
        logger.info("GET /restaurante/" + id);
        var restaurante = this.restauranteService.findRestaurante(id);
        return ResponseEntity.ok(restaurante);
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
    public ResponseEntity<Void> saveRestaurante(@RequestBody RestauranteDTO restaurante) {
        logger.info("POST /restaurante");
        this.restauranteService.saveRestaurante(restaurante);
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
    public ResponseEntity<Void> updateRestaurante(@PathVariable("id") Long id, @RequestBody RestauranteDTO restaurante) {
        logger.info("PUT /restaurante/" + id);
        this.restauranteService.updateRestaurante(id, restaurante);
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
        logger.info("DELETE /restaurante/" + id);
        this.restauranteService.deleteRestaurante(id);
        return ResponseEntity.noContent().build();
    }*/
}