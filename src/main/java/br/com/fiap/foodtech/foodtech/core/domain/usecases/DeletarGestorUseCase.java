package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IGestorGateway;

public class DeletarGestorUseCase {

    private final IGestorGateway gestorGateway;

    private DeletarGestorUseCase(IGestorGateway gestorGateway) {
        this.gestorGateway = gestorGateway;
    }

    public static DeletarGestorUseCase create(IGestorGateway gestorGateway) {
        return new DeletarGestorUseCase(gestorGateway);
    }

    public void run(Long id) {
        Gestor gestorExistente = gestorGateway.buscarPorId(id);

        if (gestorExistente == null) {
            throw new GestorNaoEncontradoException("Gestor com ID " + id + " não encontrado");
        }
        gestorGateway.deletar(id);
    }
}
