package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.UsuarioDTO;
import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.service.ClienteService;
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
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Controller responsável pelo gerenciamento dos clientes")
public class ClienteController {

    private final ClienteService clienteService;

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @Operation(
            description = "Buscar todos os clientes paginados",
            summary = "Buscar clientes",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<Cliente>> findAllClientes(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("GET /clientes");
        var clientes = this.clienteService.findAllClientes(page, size);
        return new ResponseEntity<>(clientes.getContent(), HttpStatus.OK);
    }

    @Operation(
            description = "Buscar um cliente por ID",
            summary = "Buscar cliente",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @GetMapping("/{id}")
    public Cliente findCliente(@PathVariable("id") Long id) {
        logger.info("GET /cliente/" + id);
        return this.clienteService.findCliente(id);
    }

    @Operation(
            description = "Salvar um novo cliente",
            summary = "Salvar cliente",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveCliente(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        logger.info("POST /clientes");
        this.clienteService.saveCliente(usuarioDTO);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Atualizar um cliente com base no ID informado",
            summary = "Atualizar cliente",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCliente(@PathVariable("id") Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        logger.info("PUT /clientes/" + id);
        this.clienteService.updateCliente(id, usuarioDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(
            description = "Excluir um cliente com base no ID informado",
            summary = "Deletar cliente",
            responses = {
                @ApiResponse(description = "NoContent", responseCode = "204"),
                @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
        logger.info("DELETE /clientes/" + id);
        this.clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
