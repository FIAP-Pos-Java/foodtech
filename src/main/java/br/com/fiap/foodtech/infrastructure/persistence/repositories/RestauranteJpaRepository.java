package br.com.fiap.foodtech.infrastructure.persistence.repositories;

import br.com.fiap.foodtech.infrastructure.persistence.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RestauranteJpaRepository extends JpaRepository<RestauranteEntity, Long> {


    List<RestauranteEntity> findByTipoCozinhaContainingIgnoreCase(String tipoCozinha);


    List<RestauranteEntity> findByGestorId(Long gestorId);


    @Query("SELECT r FROM RestauranteEntity r " +
            "LEFT JOIN FETCH r.gestor g " +
            "LEFT JOIN FETCH g.login " +
            "LEFT JOIN FETCH g.endereco " +
            "LEFT JOIN FETCH r.endereco " +
            "WHERE r.id = :id")
    Optional<RestauranteEntity> findByIdWithDetails(@Param("id") Long id);


    @Query("SELECT r FROM RestauranteEntity r " +
            "LEFT JOIN FETCH r.gestor g " +
            "LEFT JOIN FETCH g.login " +
            "LEFT JOIN FETCH g.endereco " +
            "LEFT JOIN FETCH r.endereco")
    List<RestauranteEntity> findAllWithDetails();


    boolean existsByNome(String nome);
}