package br.com.fiap.foodtech.infrastructure.persistence.repositories;

import br.com.fiap.foodtech.infrastructure.persistence.entities.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ItemCardapioJpaRepository extends JpaRepository<ItemCardapioEntity, Long> {


    @Query("SELECT i FROM ItemCardapioEntity i WHERE i.disponibilidadeRestaurante = :disponibilidade")
    Optional<ItemCardapioEntity> findByDisponibilidadeRestaurante(@Param("disponibilidade") Boolean disponibilidade);


    Optional<ItemCardapioEntity> findByNomeContainingIgnoreCase(String nome);


    boolean existsByNome(String nome);
}