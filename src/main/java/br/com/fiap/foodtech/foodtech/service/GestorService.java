package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.dto.UsuarioDTO;
import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Endereco;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.repositories.GestorRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import br.com.fiap.foodtech.foodtech.service.exceptions.UnauthorizedException;
import br.com.fiap.foodtech.foodtech.validation.GestorValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GestorService {

    private final GestorRepository gestorRepository;
    private final GestorValidator gestorValidator;

    public GestorService(GestorRepository gestorRepository, GestorValidator gestorValidator) {
        this.gestorRepository = gestorRepository;
        this.gestorValidator = gestorValidator;
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
        this.gestorValidator.validarLoginAndEmail(novoGestor);
        this.gestorRepository.save(novoGestor);
    }

    public void updateGestor(Long id, UsuarioDTO usuarioDTO) {
        Gestor gestorExistente = this.gestorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gestor não encontrado. ID: " + id));

        Gestor gestorAtualizado = usuarioDTO.mapearGestor();

        Endereco enderecoExistente = gestorExistente.getEndereco();
        if(enderecoExistente == null){
            enderecoExistente = new Endereco();
            gestorExistente.setEndereco(enderecoExistente);
        }

        gestorAtualizado.setId(id);
        gestorAtualizado.getEndereco().setId(enderecoExistente.getId());
        this.gestorRepository.save(gestorAtualizado);
    }

    public void deleteGestor(Long id) {
        this.gestorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gestor não encontrado. ID: " + id));
        this.gestorRepository.deleteById(id);
    }

    public Gestor validarLogin(String login, String senha) {
        Gestor gestor = gestorRepository.findByLogin(login);
        if (gestor == null) {
            throw new UnauthorizedException("Login inválido.");
        }
        if (!gestor.getSenha().equals(senha)) {
            throw new UnauthorizedException("Senha inválida.");
        }
        return gestor;
    }
}

