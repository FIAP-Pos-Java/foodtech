package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.loginDTO;
import br.com.fiap.foodtech.foodtech.dto.UsuarioDTO;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.service.GestorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/gestores")
public class GestorController {
    private final GestorService gestorService;
    private static final Logger logger = LoggerFactory.getLogger(GestorController.class);

    public GestorController(GestorService gestorService) {
        this.gestorService = gestorService;
    }

    @GetMapping
    public ResponseEntity<List<Gestor>> findAllGestors(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("GET /gestores");
        var gestores = this.gestorService.findAllGestors(page, size);
        return new ResponseEntity<>(gestores.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Gestor findGestor(@PathVariable("id") Long id) {
        logger.info("GET /gestor/" + id);
        return this.gestorService.findGestor(id);
    }

    @PostMapping
    public ResponseEntity<Void> saveGestor(@RequestBody UsuarioDTO usuarioDTO) {
        logger.info("POST /gestores");
        this.gestorService.saveGestor(usuarioDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGestor(@PathVariable("id") Long id, @RequestBody UsuarioDTO usuarioDTO) {
        logger.info("PUT /gestores/" + id);
        this.gestorService.updateGestor(id, usuarioDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGestor(@PathVariable("id") Long id) {
        logger.info("DELETE /gestores/" + id);
        this.gestorService.deleteGestor(id);
        return ResponseEntity.noContent().build();
    }
}
