package br.com.fiap.foodtech.core.adapters.controllers;

import br.com.fiap.foodtech.core.adapters.gateways.GestorGateway;
import br.com.fiap.foodtech.core.adapters.presenters.GestorPresenter;
import br.com.fiap.foodtech.core.domain.usecases.BuscarGestorPorIdUseCase;
import br.com.fiap.foodtech.core.domain.usecases.CadastrarGestorUseCase;
import br.com.fiap.foodtech.core.dto.GestorDTO;
import br.com.fiap.foodtech.core.dto.NovoGestorDTO;
import br.com.fiap.foodtech.core.exceptions.GestorJaExistenteException;
import br.com.fiap.foodtech.core.exceptions.GestorNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;

public class GestorController {
    private final IDataStorageSource dataStorageSource;

    private GestorController(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    public static GestorController create(IDataStorageSource dataStorageSource) {
        return new GestorController(dataStorageSource);
    }

    public GestorDTO cadastrar(NovoGestorDTO novoGestorDTO) {
        var gestorGateway = GestorGateway.create(this.dataStorageSource);
        var useCase = CadastrarGestorUseCase.create(gestorGateway);

        try {
            var gestor = useCase.run(novoGestorDTO);
            return GestorPresenter.toDTO(gestor);
        } catch (GestorJaExistenteException e) {
            throw e;
        }
    }

    public GestorDTO buscarPorId(Long id) {
        var gestorGateway = GestorGateway.create(this.dataStorageSource);
        var useCase = BuscarGestorPorIdUseCase.create(gestorGateway);

        try {
            var gestor = useCase.run(id);
            return GestorPresenter.toDTO(gestor);
        } catch (GestorNaoEncontradoException e) {
            throw e;
        }
    }
}