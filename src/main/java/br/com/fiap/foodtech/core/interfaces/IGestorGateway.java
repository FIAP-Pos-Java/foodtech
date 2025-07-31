package br.com.fiap.foodtech.core.interfaces;

import br.com.fiap.foodtech.core.domain.entities.Gestor;

public interface IGestorGateway {
    Gestor buscarPorId(Long id);
    Gestor buscarPorEmail(String email);
    Gestor incluir(Gestor gestor);
    Gestor atualizar(Gestor gestor);
    void deletar(Long id);
}