package br.com.fiap.foodtech.foodtech.controllers;

import br.com.fiap.foodtech.foodtech.dto.LoginDTO;
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
        logger.info("GET /gestors");
        var gestores = this.gestorService.findAllGestors(page, size);
        return new ResponseEntity<>(gestores.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Gestor findGestor(@PathVariable("id") Long id) {
        logger.info("GET /gestor/" + id);
        return this.gestorService.findGestor(id);
    }

    @PostMapping
    public ResponseEntity<Void> saveGestor(@RequestBody Gestor gestor) {
        logger.info("POST /gestors");
        this.gestorService.saveGestor(gestor);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gestor> updateGestor(@PathVariable("id") Long id, @RequestBody Gestor gestor) {
        logger.info("PUT /gestors/" + id);
        Gestor gestorAtualizado = this.gestorService.updateGestor(id, gestor);
        return ResponseEntity.ok().body(gestorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGestor(@PathVariable("id") Long id) {
        logger.info("DELETE /gestors/" + id);
        this.gestorService.deleteGestor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        logger.info("POST /gestors/login");
        Gestor gestor = this.gestorService.validarLogin(loginDTO.getLogin(), loginDTO.getSenha());
        return ResponseEntity.status(200).body("Login realizado com sucesso");
    }
}
