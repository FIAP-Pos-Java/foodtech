package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.UsuarioDTO;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.service.GestorService;
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
@RequestMapping(value = "/gestores")
@Tag(name = "Gestor", description = "Controller responsável pelo gerenciamento dos gestores")
public class GestorController {
    private final GestorService gestorService;

    private static final Logger logger = LoggerFactory.getLogger(GestorController.class);

    public GestorController(GestorService gestorService) {
        this.gestorService = gestorService;
    }

    @Operation(
            description = "Buscar todos os gestores paginados",
            summary = "Buscar gestores",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<Gestor>> findAllGestors(
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
    public Gestor findGestor(@PathVariable("id") Long id) {
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
    }
}
