package br.com.fiap.foodtech.foodtech.infrastructure.controllers;

import br.com.fiap.foodtech.foodtech.core.controllers.ClienteController;
import br.com.fiap.foodtech.foodtech.core.controllers.GestorController;
import br.com.fiap.foodtech.foodtech.core.dtos.GestorDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.GestorDataDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoGestorDTO;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.GestorEntity;
import br.com.fiap.foodtech.foodtech.infrastructure.data.repositories.DataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/gestores")
@Tag(name = "Gestor", description = "Controller responsável pelo gerenciamento dos gestores")
public class GestorResource {

    @Autowired
    DataRepository dataRepository;

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
    }*/

    @Operation(
            description = "Buscar um gestor por ID",
            summary = "Buscar gestor",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GestorDTO> findGestor(@PathVariable("id") Long id) {
        GestorController gestorController = GestorController.create(dataRepository);
        return new ResponseEntity<>(gestorController.buscarPorId(id), HttpStatus.OK);
    }

    @Operation(
            description = "Salvar um novo gestor",
            summary = "Salvar gestor",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveGestor(@Valid @RequestBody NovoGestorDTO novoGestorDTO) {
        GestorController gestorController = GestorController.create(dataRepository);
        gestorController.cadastrar(novoGestorDTO);
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
    public ResponseEntity<Void> updateGestor(@PathVariable("id") Long id, @Valid @RequestBody GestorDataDTO gestorDataDTO) {
        GestorController gestorController = GestorController.create(dataRepository);
        gestorController.atualizar(id, gestorDataDTO);
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
        GestorController gestorController = GestorController.create(dataRepository);
        gestorController.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
