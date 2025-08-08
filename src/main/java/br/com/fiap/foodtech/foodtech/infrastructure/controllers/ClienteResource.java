package br.com.fiap.foodtech.foodtech.infrastructure.controllers;

import br.com.fiap.foodtech.foodtech.core.controllers.ClienteController;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
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
@RequestMapping("/api/v1/clientes")
@Tag(name = "Cliente", description = "Controller responsável pelo gerenciamento dos clientes")
public class ClienteResource {

    @Autowired
    DataRepository dataRepository;

    @Operation(
            description = "Buscar todos os clientes paginados",
            summary = "Buscar clientes",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<Pagina<ClienteDTO>> buscarTodosClientes(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        ClienteController clienteController = ClienteController.create(dataRepository);
        Paginacao paginacao = new Paginacao(page, size);
        return new ResponseEntity<>(clienteController.buscarTodosClientes(paginacao), HttpStatus.OK);
    }

    @Operation(
            description = "Buscar um cliente por ID",
            summary = "Buscar cliente",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable("id") Long id) {
        ClienteController clienteController = ClienteController.create(dataRepository);
        return new ResponseEntity<>(clienteController.buscarPorId(id), HttpStatus.OK);
    }

    @Operation(
            description = "Salvar um novo cliente",
            summary = "Salvar cliente",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveCliente(@Valid @RequestBody NovoClienteDTO novoClienteDTO) {
        ClienteController clienteController = ClienteController.create(dataRepository);
        clienteController.cadastrar(novoClienteDTO);
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
    public ResponseEntity<Void> updateCliente(@PathVariable("id") Long id, @Valid @RequestBody ClienteDataDTO clienteDataDTO) {
        ClienteController clienteController = ClienteController.create(dataRepository);
        clienteController.atualizar(id, clienteDataDTO);
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
        ClienteController clienteController = ClienteController.create(dataRepository);
        clienteController.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
