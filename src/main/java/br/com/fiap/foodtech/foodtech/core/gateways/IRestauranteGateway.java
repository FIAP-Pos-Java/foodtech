package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;

import java.util.List;

public interface IRestauranteGateway {

    Restaurante buscarPorId(Long id);

    List<Restaurante> buscarTodos(int page, int size);

    Restaurante incluir(Restaurante restaurante);

    Restaurante atualizar(Restaurante restaurante);

    void deletar(Long id);

}
