package br.com.fiap.foodtech.foodtech.repositories;

import br.com.fiap.foodtech.foodtech.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
