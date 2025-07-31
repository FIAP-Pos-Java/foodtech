package br.com.fiap.foodtech.infrastructure.persistence.repositories;

import br.com.fiap.foodtech.infrastructure.persistence.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {

    // Buscar cliente por email
    Optional<ClienteEntity> findByEmail(String email);

    // Verificar se existe cliente com determinado email
    boolean existsByEmail(String email);

    // Query customizada para buscar cliente com login e endereço
    @Query("SELECT c FROM ClienteEntity c " +
            "LEFT JOIN FETCH c.login " +
            "LEFT JOIN FETCH c.endereco " +
            "WHERE c.id = :id")
    Optional<ClienteEntity> findByIdWithDetails(@Param("id") Long id);

    // Query customizada para buscar cliente por email com detalhes
    @Query("SELECT c FROM ClienteEntity c " +
            "LEFT JOIN FETCH c.login " +
            "LEFT JOIN FETCH c.endereco " +
            "WHERE c.email = :email")
    Optional<ClienteEntity> findByEmailWithDetails(@Param("email") String email);
}