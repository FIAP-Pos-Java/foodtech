package br.com.fiap.foodtech.foodtech.repositories;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

//    boolean existsClienteByEmail(String email);
//
//    boolean existsClienteById(Long id);
//
//    Cliente findByLogin(String login);

}