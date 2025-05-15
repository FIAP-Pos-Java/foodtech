package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> buscarCliente() {
        return this.clienteService.buscarCliente();
    }

    @PostMapping
    public Cliente salvandoCliente(@RequestBody Cliente cliente) {
        return this.clienteService.salvandoCliente(cliente);
    }

    @PutMapping("{id}")
    public void atualizaCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        this.clienteService.atualizarCliente(id, cliente);
    }

    @DeleteMapping("{id}")
    public void deleteCliente(@PathVariable("id") Long id) {
        this.clienteService.deletandoCliente(id);
    }
}
