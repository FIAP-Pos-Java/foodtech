package br.com.fiap.foodtech.foodtech.repositories;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
