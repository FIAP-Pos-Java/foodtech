package br.com.fiap.foodtech.foodtech.repositories;

import br.com.fiap.foodtech.foodtech.entities.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {

    boolean existsGestorByLogin(String login);

    boolean existsGestorByEmail(String email);

    boolean existsGestorById(Long id);

    Gestor findByLogin(String login);
}