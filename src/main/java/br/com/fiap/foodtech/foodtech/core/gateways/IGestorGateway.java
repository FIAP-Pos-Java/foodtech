package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.dtos.Pagina;
import br.com.fiap.foodtech.foodtech.core.dtos.Paginacao;

public interface IGestorGateway {

    Pagina<Gestor> buscarTodos(Paginacao paginacao);

    Gestor buscarPorId(Long id);

    Gestor buscarPorEmail(String email);

    Gestor incluir(Gestor gestor);

    Gestor atualizar(Gestor gestor);

    void deletar(Long id);

}
