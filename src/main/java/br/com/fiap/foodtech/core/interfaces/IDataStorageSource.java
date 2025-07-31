package br.com.fiap.foodtech.core.interfaces;
import br.com.fiap.foodtech.core.dto.*;

public interface IDataStorageSource {
    ClienteDataDTO obterClientePorId(Long id);
    ClienteDataDTO obterClientePorEmail(String email);
    ClienteDataDTO incluirCliente(NovoClienteDTO novoCliente);
    void deletarCliente(Long id);

    GestorDataDTO obterGestorPorId(Long id);
    GestorDataDTO obterGestorPorEmail(String email);
    GestorDataDTO incluirGestor(NovoGestorDTO novoGestor);
    void deletarGestor(Long id);

    LoginDataDTO obterLoginPorLogin(String login);
    void atualizarLogin(Long id, String novaSenha);
}
