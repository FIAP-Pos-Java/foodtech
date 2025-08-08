package br.com.fiap.foodtech.foodtech.infrastructure.data.repositories;

import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    ClienteEntity findByEmail(String email);

}
