package br.com.fiap.foodtech.core.domain.usecases;

import br.com.fiap.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.core.dto.NovoGestorDTO;
import br.com.fiap.foodtech.core.exceptions.GestorJaExistenteException;
import br.com.fiap.foodtech.core.interfaces.IGestorGateway;

public class CadastrarGestorUseCase {
    private final IGestorGateway gestorGateway;

    private CadastrarGestorUseCase(IGestorGateway gestorGateway) {
        this.gestorGateway = gestorGateway;
    }

    public static CadastrarGestorUseCase create(IGestorGateway gestorGateway) {
        return new CadastrarGestorUseCase(gestorGateway);
    }

    public Gestor run(NovoGestorDTO novoGestorDTO) throws GestorJaExistenteException {

        try {
            Gestor gestorExistente = gestorGateway.buscarPorEmail(novoGestorDTO.email());
            if (gestorExistente != null) {
                throw new GestorJaExistenteException("Gestor com email " + novoGestorDTO.email() + " já existe");
            }
        } catch (Exception e) {

        }


        Login login = Login.create(novoGestorDTO.login(), novoGestorDTO.senha());
        Endereco endereco = Endereco.create(
                novoGestorDTO.logradouro(),
                novoGestorDTO.numero(),
                novoGestorDTO.bairro(),
                novoGestorDTO.cidade(),
                novoGestorDTO.estado(),
                novoGestorDTO.cep()
        );

        Gestor novoGestor = Gestor.create(
                novoGestorDTO.nome(),
                novoGestorDTO.email(),
                login,
                endereco
        );

        return gestorGateway.incluir(novoGestor);
    }
}