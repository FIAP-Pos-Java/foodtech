package br.com.fiap.foodtech.foodtech.repositories;

import br.com.fiap.foodtech.foodtech.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
    boolean existsLoginByLogin(String login);
}
