package br.com.fiap.foodtech.core.adapters.gateways;

import br.com.fiap.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.core.dto.NovoGestorDTO;
import br.com.fiap.foodtech.core.exceptions.GestorNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IGestorGateway;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;

public class GestorGateway implements IGestorGateway {
    private final IDataStorageSource dataStorageSource;

    private GestorGateway(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    public static GestorGateway create(IDataStorageSource dataStorageSource) {
        return new GestorGateway(dataStorageSource);
    }

    @Override
    public Gestor buscarPorId(Long id) {
        var gestorData = dataStorageSource.obterGestorPorId(id);
        if (gestorData == null) {
            throw new GestorNaoEncontradoException("Gestor com ID " + id + " não encontrado");
        }

        Login login = Login.create(gestorData.loginData().login(), gestorData.loginData().senha());
        Endereco endereco = Endereco.create(
                gestorData.enderecoData().logradouro(),
                gestorData.enderecoData().numero(),
                gestorData.enderecoData().bairro(),
                gestorData.enderecoData().cidade(),
                gestorData.enderecoData().estado(),
                gestorData.enderecoData().cep()
        );

        return Gestor.create(
                gestorData.id(),
                gestorData.nome(),
                gestorData.email(),
                gestorData.tipoUsuario(),
                login,
                endereco
        );
    }

    @Override
    public Gestor buscarPorEmail(String email) {
        var gestorData = dataStorageSource.obterGestorPorEmail(email);
        if (gestorData == null) {
            return null;
        }

        Login login = Login.create(gestorData.loginData().login(), gestorData.loginData().senha());
        Endereco endereco = Endereco.create(
                gestorData.enderecoData().logradouro(),
                gestorData.enderecoData().numero(),
                gestorData.enderecoData().bairro(),
                gestorData.enderecoData().cidade(),
                gestorData.enderecoData().estado(),
                gestorData.enderecoData().cep()
        );

        return Gestor.create(
                gestorData.id(),
                gestorData.nome(),
                gestorData.email(),
                gestorData.tipoUsuario(),
                login,
                endereco
        );
    }

    @Override
    public Gestor incluir(Gestor gestor) {
        NovoGestorDTO novoGestorDTO = new NovoGestorDTO(
                gestor.getNome(),
                gestor.getEmail(),
                gestor.getLogin().getLogin(),
                gestor.getLogin().getSenha(),
                gestor.getEndereco().getLogradouro(),
                gestor.getEndereco().getNumero(),
                gestor.getEndereco().getBairro(),
                gestor.getEndereco().getCidade(),
                gestor.getEndereco().getEstado(),
                gestor.getEndereco().getCep()
        );

        var gestorSalvo = dataStorageSource.incluirGestor(novoGestorDTO);

        Login login = Login.create(gestorSalvo.loginData().login(), gestorSalvo.loginData().senha());
        Endereco endereco = Endereco.create(
                gestorSalvo.enderecoData().logradouro(),
                gestorSalvo.enderecoData().numero(),
                gestorSalvo.enderecoData().bairro(),
                gestorSalvo.enderecoData().cidade(),
                gestorSalvo.enderecoData().estado(),
                gestorSalvo.enderecoData().cep()
        );

        return Gestor.create(
                gestorSalvo.id(),
                gestorSalvo.nome(),
                gestorSalvo.email(),
                gestorSalvo.tipoUsuario(),
                login,
                endereco
        );
    }

    @Override
    public Gestor atualizar(Gestor gestor) {

        throw new UnsupportedOperationException("Não implementado ainda");
    }

    @Override
    public void deletar(Long id) {
        dataStorageSource.deletarGestor(id);
    }
}