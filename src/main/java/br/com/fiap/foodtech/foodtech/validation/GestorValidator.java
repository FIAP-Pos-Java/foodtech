package br.com.fiap.foodtech.foodtech.validation;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.repositories.GestorRepository;
import org.springframework.stereotype.Component;

@Component
public class GestorValidator {

//    private final GestorRepository gestorRepository;
//
//    public GestorValidator(GestorRepository gestorRepository) {
//        this.gestorRepository = gestorRepository;
//    }
//
//    private boolean existeGestorComEmail(String email){
//        return gestorRepository.existsGestorByEmail(email);
//    }
//
//    private boolean existeIdGestor(Long id){
//        return gestorRepository.existsGestorById(id);
//    }
//
//    public void validarEmail(Gestor gestor){
//        if (existeGestorComEmail(gestor.getEmail())) {
//            throw new IllegalArgumentException("esse cliente ja existe, entrar em contato com o suporte");
//        }
//    }
//
//    public void validarId(Long id){
//        if(!existeIdGestor(id)){
//            throw new IllegalArgumentException("esse gestor não existe na base de dados");
//        }
//    }
}

