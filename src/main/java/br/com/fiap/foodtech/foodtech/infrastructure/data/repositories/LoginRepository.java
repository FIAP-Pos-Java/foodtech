package br.com.fiap.foodtech.foodtech.infrastructure.data.repositories;

import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    boolean existsLoginByLogin(String login);
    Optional<LoginEntity>  findByLogin(String login);
}
