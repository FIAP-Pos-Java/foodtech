package br.com.fiap.foodtech.foodtech.core.gateways;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Endereco;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.domain.entities.Login;
import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.core.exceptions.gestor.GestorNaoEncontradoException;
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
        return dtoToGestor(gestorData);
    }

    @Override
    public Gestor buscarPorEmail(String email) {
        var gestorData = dataSource.obterGestorPorEmail(email);
        if (gestorData == null) {
            return null;
        }
        return dtoToGestor(gestorData);
    }

    @Override
    public Gestor incluir(Gestor gestor) {
        NovoGestorDTO novoGestorDTO = new NovoGestorDTO(
                gestor.getNome(),
                gestor.getEmail(),
                gestor.getTipoUsuario(),
                new NovoLoginDTO(
                        gestor.getLogin().getLogin(),
                        gestor.getLogin().getSenha()
                ),
                new NovoEnderecoDTO(
                        gestor.getEndereco().getLogradouro(),
                        gestor.getEndereco().getNumero(),
                        gestor.getEndereco().getBairro(),
                        gestor.getEndereco().getCidade(),
                        gestor.getEndereco().getEstado(),
                        gestor.getEndereco().getCep()
                )
        );
        var gestorSalvo = dataSource.incluirGestor(novoGestorDTO);
        return dtoToGestor(gestorSalvo);
    }

    @Override
    public Gestor atualizar(Gestor gestor) {
        GestorDataDTO gestorDataDTO = new GestorDataDTO(
                gestor.getId(),
                gestor.getNome(),
                gestor.getEmail(),
                gestor.getTipoUsuario(),
                new LoginDataDTO(
                        gestor.getLogin().getId(),
                        gestor.getLogin().getLogin()
                ),
                new EnderecoDataDTO(
                        gestor.getEndereco().getId(),
                        gestor.getEndereco().getLogradouro(),
                        gestor.getEndereco().getNumero(),
                        gestor.getEndereco().getBairro(),
                        gestor.getEndereco().getCidade(),
                        gestor.getEndereco().getEstado(),
                        gestor.getEndereco().getCep()
                )
        );
        var gestorAtualizado = dataSource.atualizarGestor(gestorDataDTO);
        return dtoToGestor(gestorAtualizado);
    }

    @Override
    public void deletar(Long id) {
        dataSource.deletarGestor(id);
    }

    private Gestor dtoToGestor(GestorDataDTO gestorSalvo) {
        Login login = new Login(gestorSalvo.loginData().id(), gestorSalvo.loginData().login());

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
}
