package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.dtos.Pagina;
import br.com.fiap.foodtech.foodtech.core.dtos.Paginacao;

public interface IClienteGateway {

    Pagina<Cliente> buscarTodos(Paginacao paginacao);

    Cliente buscarPorId(Long id);

    Cliente buscarPorEmail(String email);

    Cliente incluir(Cliente cliente);

    Cliente atualizar(Cliente cliente);

    void deletar(Long id);

}
