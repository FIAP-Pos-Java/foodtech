package br.com.fiap.foodtech.core.interfaces;

import br.com.fiap.foodtech.core.domain.entities.Cliente;

public interface IClienteGateway {
    Cliente buscarPorId(Long id);
    Cliente buscarPorEmail(String email);
    Cliente incluir(Cliente cliente);
    Cliente atualizar(Cliente cliente);
    void deletar(Long id);
}