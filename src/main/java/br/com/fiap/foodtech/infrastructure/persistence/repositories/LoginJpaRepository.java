package br.com.fiap.foodtech.infrastructure.persistence.repositories;

import br.com.fiap.foodtech.infrastructure.persistence.entities.GestorEntity;
import br.com.fiap.foodtech.infrastructure.persistence.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginJpaRepository extends JpaRepository<LoginEntity, Long> {

    // Buscar login por nome de usuário - essencial para autenticação
    Optional<LoginEntity> findByLogin(String login);

    // Verificar se existe um login com determinado nome de usuário
    // Útil para evitar duplicatas na criação de usuários
    boolean existsByLogin(String login);

    /*
     * CONCEITO PEDAGÓGICO - Por que ter um repositório separado para Login?
     *
     * Imagine que você tem uma caixa de ferramentas (repositório).
     * Você pode ter uma caixa grande com todas as ferramentas misturadas,
     * ou várias caixas pequenas, cada uma especializada em um tipo de ferramenta.
     *
     * O LoginJpaRepository é como uma caixa especializada em "ferramentas de autenticação".
     * Isso facilita:
     * 1. Encontrar operações relacionadas a login
     * 2. Reutilizar em diferentes contextos (Cliente e Gestor)
     * 3. Manter o código organizado por responsabilidade
     *
     * Além disso, seguindo os princípios SOLID:
     * - Single Responsibility: cada repositório tem uma responsabilidade específica
     * - Open/Closed: podemos adicionar novos métodos sem modificar os existentes
     */
}