package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.service.ClienteService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ClienteController.class);

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> buscarCliente() {
        logger.info("GET -> /clientes/");
        return this.clienteService.buscarCliente();
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> salvandoCliente(@RequestBody Cliente cliente) {
        try{
            logger.info("POST -> /clientes/");
            this.clienteService.salvandoCliente(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "cliente salvo com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Map<String, String>> atualizaCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        try{
            logger.info("PUT -> /clientes/"+id);
            this.clienteService.atualizarCliente(id, cliente);
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "Cliente atualizado com sucesso"));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> deleteCliente(@PathVariable("id") Long id) {
       try{
           logger.info("DELETE -> /clientes/"+id);
           this.clienteService.deletandoCliente(id);
           return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message", "cliente deletado com sucesso"));
       }catch (IllegalArgumentException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
       }
    }
}
