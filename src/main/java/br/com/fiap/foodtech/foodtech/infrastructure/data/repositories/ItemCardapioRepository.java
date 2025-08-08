package br.com.fiap.foodtech.foodtech.infrastructure.data.repositories;

import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapioEntity, Long> {


}