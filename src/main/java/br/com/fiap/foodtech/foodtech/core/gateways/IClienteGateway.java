package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;

public interface IClienteGateway {

    Cliente buscarPorId(Long id);

    Cliente buscarPorEmail(String email);

    Cliente incluir(Cliente cliente);

    Cliente atualizar(Cliente cliente);

    void deletar(Long id);

}
