package br.com.fiap.foodtech.foodtech.core.controllers;

import br.com.fiap.foodtech.foodtech.core.domain.usecases.*;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorJaExistenteException;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.ClienteGateway;
import br.com.fiap.foodtech.foodtech.core.gateways.GestorGateway;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;
import br.com.fiap.foodtech.foodtech.core.presenters.ClientePresenter;
import br.com.fiap.foodtech.foodtech.core.presenters.GestorPresenter;

public class GestorController {

    private final DataSource dataSource;

    private GestorController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static GestorController create(DataSource dataSource) {
        return new GestorController(dataSource);
    }


    public Pagina<GestorDTO> buscarTodosGestores(Paginacao paginacao) {
        var gestorGateway = GestorGateway.create(this.dataSource);
        var useCase = new ListarGestoresUseCase(gestorGateway);

        var paginaGestor = useCase.run(paginacao);
        return new Pagina<>(paginaGestor.content().stream().map(GestorPresenter::toDTO).toList(), paginaGestor.totalElements());
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

    public GestorDTO cadastrar(NovoGestorDTO novoGestorDTO) {
        var gestorGateway = GestorGateway.create(this.dataSource);
        var useCase = CadastrarGestorUseCase.create(gestorGateway);

        var gestor = useCase.run(novoGestorDTO);
        return GestorPresenter.toDTO(gestor);
    }

    public GestorDTO atualizar(Long id, GestorDataDTO gestorDataDTO) {
        var gestorGateway = GestorGateway.create(this.dataSource);
        var useCase = AtualizarGestorUseCase.create(gestorGateway);

        var gestor = useCase.run(id, gestorDataDTO);
        return GestorPresenter.toDTO(gestor);
    }

    public void deletar(Long id) {
        var gestorGateway = GestorGateway.create(this.dataSource);
        var useCase = DeletarGestorUseCase.create(gestorGateway);

        useCase.run(id);
    }
}
