package br.com.fiap.foodtech.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoGestorDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorJaExistenteException;
import br.com.fiap.foodtech.foodtech.core.gateways.IGestorGateway;

public class CadastrarGestorUseCase {

    private final IGestorGateway gestorGateway;

    private CadastrarGestorUseCase(IGestorGateway gestorGateway) {
        this.gestorGateway = gestorGateway;
    }

    public static CadastrarGestorUseCase create(IGestorGateway gestorGateway) {
        return new CadastrarGestorUseCase(gestorGateway);
    }

    public Gestor run(NovoGestorDTO novoGestorDTO) throws GestorJaExistenteException {
        Gestor gestorExistente = gestorGateway.buscarPorEmail(novoGestorDTO.email());

        if (gestorExistente != null) {
            throw new GestorJaExistenteException("Gestor com email " + novoGestorDTO.email() + " já existe");
        }

        Login login = new Login(
                novoGestorDTO.login().login(),
                novoGestorDTO.login().senha()
        );

        Endereco endereco = new Endereco(
                novoGestorDTO.endereco().logradouro(),
                novoGestorDTO.endereco().numero(),
                novoGestorDTO.endereco().bairro(),
                novoGestorDTO.endereco().cidade(),
                novoGestorDTO.endereco().estado(),
                novoGestorDTO.endereco().cep()
        );

        Gestor novoGestor = new Gestor(
                novoGestorDTO.nome(),
                novoGestorDTO.email(),
                novoGestorDTO.tipoUsuario(),
                endereco,
                login
        );

        return gestorGateway.incluir(novoGestor);
    }

}
