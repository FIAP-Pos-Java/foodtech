package br.com.fiap.foodtech.foodtech.core.controllers;

import br.com.fiap.foodtech.foodtech.core.domain.usecases.BuscarGestorPorIdUseCase;
import br.com.fiap.foodtech.foodtech.core.domain.usecases.CadastrarGestorUseCase;
import br.com.fiap.foodtech.foodtech.core.dtos.GestorDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoGestorDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.GestorJaExistenteException;
import br.com.fiap.foodtech.foodtech.core.exceptions.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.GestorGateway;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;
import br.com.fiap.foodtech.foodtech.core.presenters.GestorPresenter;

public class GestorController {

    private final DataSource dataSource;

    private GestorController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static GestorController create(DataSource dataSource) {
        return new GestorController(dataSource);
    }

    public GestorDTO cadastrar(NovoGestorDTO novoGestorDTO) {
        var gestorGateway = GestorGateway.create(this.dataSource);
        var useCase = CadastrarGestorUseCase.create(gestorGateway);

        try {
            var gestor = useCase.run(novoGestorDTO);
            return GestorPresenter.toDTO(gestor);
        } catch (GestorJaExistenteException e) {
            throw e;
        }
    }

    public GestorDTO buscarPorId(Long id) {
        var gestorGateway = GestorGateway.create(this.dataSource);
        var useCase = BuscarGestorPorIdUseCase.create(gestorGateway);

        try {
            var gestor = useCase.run(id);
            return GestorPresenter.toDTO(gestor);
        } catch (GestorNaoEncontradoException e) {
            throw e;
        }
    }

}
