package br.com.fiap.foodtech.foodtech.infrastructure.data.repositories;

import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    boolean existsClienteByEmail(String email);

    ClienteEntity findByEmail(String email);

}
