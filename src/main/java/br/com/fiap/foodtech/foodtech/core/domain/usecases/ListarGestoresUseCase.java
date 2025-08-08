package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.dtos.Pagina;
import br.com.fiap.foodtech.foodtech.core.dtos.Paginacao;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IGestorGateway;

public class ListarGestoresUseCase {

    private final IGestorGateway gestorGateway;

    public ListarGestoresUseCase(IGestorGateway gestorGateway) {
        this.gestorGateway = gestorGateway;
    }

    public static ListarGestoresUseCase create(IGestorGateway gestorGateway) {
        return new ListarGestoresUseCase(gestorGateway);
    }

    public Pagina<Gestor> run(Paginacao paginacao) throws GestorNaoEncontradoException {
        return gestorGateway.buscarTodos(paginacao);
    }

}
