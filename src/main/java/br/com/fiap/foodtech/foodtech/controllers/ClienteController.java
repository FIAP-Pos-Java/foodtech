package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.UsuarioDTO;
import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.foodtech.foodtech.dto.loginDTO;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);


    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAllClientes(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("GET /clientes");
        var clientes = this.clienteService.findAllClientes(page, size);
        return new ResponseEntity<>(clientes.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Cliente findCliente(@PathVariable("id") Long id) {
        logger.info("GET /cliente/" + id);
        return this.clienteService.findCliente(id);
    }

    @PostMapping
    public ResponseEntity<Void> saveCliente(@RequestBody UsuarioDTO usuarioDTO) {
        logger.info("POST /clientes");
        this.clienteService.saveCliente(usuarioDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCliente(@PathVariable("id") Long id, @RequestBody UsuarioDTO usuarioDTO) {
        logger.info("PUT /clientes/" + id);
        this.clienteService.updateCliente(id, usuarioDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
        logger.info("DELETE /clientes/" + id);
        this.clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
