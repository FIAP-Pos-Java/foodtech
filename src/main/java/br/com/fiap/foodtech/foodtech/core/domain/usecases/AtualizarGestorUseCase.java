package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.GestorDataDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorJaExistenteException;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.gateways.IGestorGateway;

public class AtualizarGestorUseCase {

    private final IGestorGateway gestorGateway;

    private AtualizarGestorUseCase(IGestorGateway gestorGateway) {
        this.gestorGateway = gestorGateway;
    }

    public static AtualizarGestorUseCase create(IGestorGateway gestorGateway) {
        return new AtualizarGestorUseCase(gestorGateway);
    }

    public Gestor run(Long id, GestorDataDTO gestorDataDTO) throws GestorJaExistenteException {
        Gestor gestorExistente = gestorGateway.buscarPorId(id);

        if (gestorExistente == null) {
            throw new GestorNaoEncontradoException("Gestor com ID " + id + " não encontrado");
        }

        Login login = new Login(
                gestorExistente.getLogin().getId(),
                gestorDataDTO.loginData().login()
        );

        Endereco endereco = new Endereco(
                gestorExistente.getEndereco().getId(),
                gestorDataDTO.enderecoData().logradouro(),
                gestorDataDTO.enderecoData().numero(),
                gestorDataDTO.enderecoData().bairro(),
                gestorDataDTO.enderecoData().cidade(),
                gestorDataDTO.enderecoData().estado(),
                gestorDataDTO.enderecoData().cep()
        );

        Gestor gestorAtualizado = new Gestor(
                gestorExistente.getId(),
                gestorDataDTO.nome(),
                gestorDataDTO.email(),
                gestorDataDTO.tipoUsuario(),
                endereco,
                login
        );
        return gestorGateway.atualizar(gestorAtualizado);
    }
}
