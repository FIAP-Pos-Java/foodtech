package br.com.fiap.foodtech.infrastructure.persistence.repositories;

import br.com.fiap.foodtech.infrastructure.persistence.entities.GestorEntity;
import br.com.fiap.foodtech.infrastructure.persistence.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * GestorJpaRepository - Operações de banco para Gestor.
 *
 * Conceito pedagógico: Repositórios são como "bibliotecários especializados".
 * Eles sabem exatamente onde encontrar e como guardar informações específicas.
 *
 * O JpaRepository já nos dá métodos básicos como save(), findById(), delete().
 * Nós adicionamos métodos específicos para nossas necessidades de negócio.
 */
@Repository
public interface GestorJpaRepository extends JpaRepository<GestorEntity, Long> {

    // Buscar gestor por email - útil para verificar duplicatas
    Optional<GestorEntity> findByEmail(String email);

    // Verificar se existe gestor com determinado email
    boolean existsByEmail(String email);

    // Query customizada para buscar gestor com todos os detalhes relacionados
    // O FETCH faz com que login e endereco sejam carregados em uma única consulta
    @Query("SELECT g FROM GestorEntity g " +
            "LEFT JOIN FETCH g.login " +
            "LEFT JOIN FETCH g.endereco " +
            "WHERE g.id = :id")
    Optional<GestorEntity> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT g FROM GestorEntity g " +
            "LEFT JOIN FETCH g.login " +
            "LEFT JOIN FETCH g.endereco " +
            "WHERE g.email = :email")
    Optional<GestorEntity> findByEmailWithDetails(@Param("email") String email);
}