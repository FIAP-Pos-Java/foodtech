package br.com.fiap.foodtech.foodtech.infrastructure.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/gestores")
@Tag(name = "Gestor", description = "Controller responsável pelo gerenciamento dos gestores")
public class GestorResource {

    private static final Logger logger = LoggerFactory.getLogger(GestorResource.class);

    /*@Operation(
            description = "Buscar todos os gestores paginados",
            summary = "Buscar gestores",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<GestorEntity>> findAllGestors(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("GET /gestores");
        var gestores = this.gestorService.findAllGestors(page, size);
        return new ResponseEntity<>(gestores.getContent(), HttpStatus.OK);
    }

    @Operation(
            description = "Buscar um gestor por ID",
            summary = "Buscar gestor",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @GetMapping("/{id}")
    public GestorEntity findGestor(@PathVariable("id") Long id) {
        logger.info("GET /gestor/" + id);
        return this.gestorService.findGestor(id);
    }

    @Operation(
            description = "Salvar um novo gestor",
            summary = "Salvar gestor",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveGestor(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        logger.info("POST /gestores");
        this.gestorService.saveGestor(usuarioDTO);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Atualizar um gestor com base no ID informado",
            summary = "Atualizar gestor",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGestor(@PathVariable("id") Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        logger.info("PUT /gestores/" + id);
        this.gestorService.updateGestor(id, usuarioDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(
            description = "Excluir um gestor com base no ID informado",
            summary = "Deletar gestor",
            responses = {
                @ApiResponse(description = "NoContent", responseCode = "204"),
                @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGestor(@PathVariable("id") Long id) {
        logger.info("DELETE /gestores/" + id);
        this.gestorService.deleteGestor(id);
        return ResponseEntity.noContent().build();
    }*/
}
