package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;

public interface IGestorGateway {

    Gestor buscarPorId(Long id);

    Gestor buscarPorEmail(String email);

    Gestor incluir(Gestor gestor);

    Gestor atualizar(Gestor gestor);

    void deletar(Long id);

}
