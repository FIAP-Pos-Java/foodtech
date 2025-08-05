package br.com.fiap.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.core.exceptions.GestorNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IGestorGateway;

public class BuscarGestorPorIdUseCase {
    private final IGestorGateway gestorGateway;

    private BuscarGestorPorIdUseCase(IGestorGateway gestorGateway) {
        this.gestorGateway = gestorGateway;
    }

    public static BuscarGestorPorIdUseCase create(IGestorGateway gestorGateway) {
        return new BuscarGestorPorIdUseCase(gestorGateway);
    }

    public Gestor run(Long id) throws GestorNaoEncontradoException {
        Gestor gestor = gestorGateway.buscarPorId(id);
        if (gestor == null) {
            throw new GestorNaoEncontradoException("Gestor com ID " + id + " não encontrado");
        }
        return gestor;
    }
}