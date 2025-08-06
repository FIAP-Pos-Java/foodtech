package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoGestorDTO;
import br.com.fiap.foodtech.foodtech.core.exceptions.GestorNaoEncontradoException;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;

public class GestorGateway implements IGestorGateway {

    private final DataSource dataSource;

    private GestorGateway(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static GestorGateway create(DataSource dataStorageSource) {
        return new GestorGateway(dataStorageSource);
    }

    @Override
    public Gestor buscarPorId(Long id) {
        var gestorData = dataSource.obterGestorPorId(id);
        if (gestorData == null) {
            throw new GestorNaoEncontradoException("Gestor com ID " + id + " não encontrado");
        }

        Login login = new Login(gestorData.loginData().login(), gestorData.loginData().senha());
        Endereco endereco = new Endereco(
                gestorData.enderecoData().logradouro(),
                gestorData.enderecoData().numero(),
                gestorData.enderecoData().bairro(),
                gestorData.enderecoData().cidade(),
                gestorData.enderecoData().estado(),
                gestorData.enderecoData().cep()
        );

        return new Gestor(
                gestorData.id(),
                gestorData.nome(),
                gestorData.email(),
                gestorData.tipoUsuario(),
                endereco,
                login
        );
    }

    @Override
    public Gestor buscarPorEmail(String email) {
        var gestorData = dataSource.obterGestorPorEmail(email);
        if (gestorData == null) {
            return null;
        }

        Login login = new Login(gestorData.loginData().login(), gestorData.loginData().senha());
        Endereco endereco = new Endereco(
                gestorData.enderecoData().logradouro(),
                gestorData.enderecoData().numero(),
                gestorData.enderecoData().bairro(),
                gestorData.enderecoData().cidade(),
                gestorData.enderecoData().estado(),
                gestorData.enderecoData().cep()
        );

        return new Gestor(
                gestorData.id(),
                gestorData.nome(),
                gestorData.email(),
                gestorData.tipoUsuario(),
                endereco,
                login
        );
    }

    @Override
    public Gestor incluir(Gestor gestor) {
        NovoGestorDTO novoGestorDTO = new NovoGestorDTO(
                gestor.getNome(),
                gestor.getEmail(),
                gestor.getTipoUsuario(),
                gestor.getLogin().getLogin(),
                gestor.getLogin().getSenha(),
                gestor.getEndereco().getLogradouro(),
                gestor.getEndereco().getNumero(),
                gestor.getEndereco().getBairro(),
                gestor.getEndereco().getCidade(),
                gestor.getEndereco().getEstado(),
                gestor.getEndereco().getCep()
        );

        var gestorSalvo = dataSource.incluirGestor(novoGestorDTO);

        Login login = new Login(gestorSalvo.loginData().login(), gestorSalvo.loginData().senha());
        Endereco endereco = new Endereco(
                gestorSalvo.enderecoData().logradouro(),
                gestorSalvo.enderecoData().numero(),
                gestorSalvo.enderecoData().bairro(),
                gestorSalvo.enderecoData().cidade(),
                gestorSalvo.enderecoData().estado(),
                gestorSalvo.enderecoData().cep()
        );

        return new Gestor(
                gestorSalvo.id(),
                gestorSalvo.nome(),
                gestorSalvo.email(),
                gestorSalvo.tipoUsuario(),
                endereco,
                login
        );
    }

    @Override
    public Gestor atualizar(Gestor gestor) {

        throw new UnsupportedOperationException("Não implementado ainda");
    }

    @Override
    public void deletar(Long id) {
        dataSource.deletarGestor(id);
    }

}
