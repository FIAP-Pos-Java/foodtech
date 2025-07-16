package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.repositories.RestauranteRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Page<Restaurante> findAllRestaurantes(int page, int size) {
        var pageable = PageRequest.of(page, size);
        return this.restauranteRepository.findAll(pageable);
    }

    public Restaurante findRestaurante(Long id) {
        return this.restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado. ID: " + id));
    }

    public void saveRestaurante(Restaurante restaurante) {
        this.restauranteRepository.save(restaurante);
    }

    public void updateRestaurante(Long id, Restaurante restaurante) {
        this.restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado. ID: " + id));

        restaurante.setId(id);
        this.restauranteRepository.save(restaurante);
    }

    public void deleteRestaurante(Long id) {
        Restaurante restaurante = this.restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado. ID: " + id));
        this.restauranteRepository.delete(restaurante);
    }
}
