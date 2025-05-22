package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dtos.AtualizarClienteDTO;
import br.com.fiap.foodtech.foodtech.dtos.SalvarClienteDTO;
import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.foodtech.foodtech.dto.LoginDTO;

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
//        clienteService.findAllClientes(page, size);
        var clientes = this.clienteService.findAllClientes(page, size);
        return new ResponseEntity<>(clientes.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Cliente findCliente(@PathVariable("id") Long id) {
        logger.info("GET /cliente/" + id);
        return this.clienteService.findCliente(id);
    }

    @PostMapping
    public ResponseEntity<Void> saveCliente(@RequestBody SalvarClienteDTO clienteDTO) {
        logger.info("POST /clientes");
        var cliente = clienteDTO.mapearSalvarCliente();
        this.clienteService.saveCliente(cliente);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") Long id, @RequestBody AtualizarClienteDTO atualizarClienteDTO) {
        logger.info("PUT /clientes/" + id);
        Cliente cliente = this.clienteService.updateCliente(id, atualizarClienteDTO);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
        logger.info("DELETE /clientes/" + id);
        this.clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        logger.info("POST /clientes/login");
        Cliente cliente = this.clienteService.validarLogin(loginDTO.getLogin(), loginDTO.getSenha());
        return ResponseEntity.status(200).body("Login realizado com sucesso");
    }

}
