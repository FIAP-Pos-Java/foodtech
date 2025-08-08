package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.dtos.Pagina;
import br.com.fiap.foodtech.foodtech.core.dtos.Paginacao;

import java.util.List;

public interface IRestauranteGateway {

    Restaurante buscarPorId(Long id);

    Pagina<Restaurante> buscarTodos(Paginacao paginacao);

    Restaurante incluir(Restaurante restaurante);

    Restaurante atualizar(Restaurante restaurante);

    void deletar(Long id);

}
