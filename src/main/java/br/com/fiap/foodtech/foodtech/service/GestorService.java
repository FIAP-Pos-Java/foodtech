package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.dto.UsuarioDTO;
import br.com.fiap.foodtech.foodtech.entities.Endereco;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.entities.Login;
import br.com.fiap.foodtech.foodtech.repositories.GestorRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import br.com.fiap.foodtech.foodtech.validation.UsuarioValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GestorService {

    private final GestorRepository gestorRepository;
    private final UsuarioValidator usuarioValidator;

    public GestorService(GestorRepository gestorRepository, UsuarioValidator usuarioValidator) {
        this.gestorRepository = gestorRepository;
        this.usuarioValidator = usuarioValidator;
    }

    public Page<Gestor> findAllGestors(int page, int size) {
        var pageable = PageRequest.of(page, size);
        return this.gestorRepository.findAll(pageable);
    }

    public Gestor findGestor(Long id) {
        return this.gestorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gestor não encontrado. ID: " + id));
    }

    public void saveGestor(UsuarioDTO usuarioDTO) {
        Gestor novoGestor = usuarioDTO.mapearGestor();
        this.usuarioValidator.validarEmail(novoGestor);
        this.gestorRepository.save(novoGestor);
    }

    public void updateGestor(Long id, UsuarioDTO usuarioDTO) {
        Gestor gestorExistente = this.gestorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gestor não encontrado. ID: " + id));

        Gestor gestorAtualizado = usuarioDTO.mapearGestor();
        Login loginAtualizado = usuarioDTO.mapearGestor().getLogin();

        Login loginExistente = gestorExistente.getLogin();
        if(loginExistente == null){
            loginExistente = new Login();
            loginExistente.setLogin(loginAtualizado.getLogin());
        }

        Endereco enderecoExistente = gestorExistente.getEndereco();
        if(enderecoExistente == null){
            enderecoExistente = new Endereco();
            gestorExistente.setEndereco(enderecoExistente);
        }

        gestorAtualizado.setId(id);
        gestorAtualizado.getLogin().setId(loginExistente.getId());
        gestorAtualizado.getEndereco().setId(enderecoExistente.getId());
        this.gestorRepository.save(gestorAtualizado);
    }

    public void deleteGestor(Long id) {
        this.gestorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gestor não encontrado. ID: " + id));
        this.gestorRepository.deleteById(id);
    }
}

