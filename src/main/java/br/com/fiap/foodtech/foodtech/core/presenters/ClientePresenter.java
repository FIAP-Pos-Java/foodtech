package br.com.fiap.foodtech.foodtech.core.presenters;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.foodtech.core.dtos.ClienteDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.EnderecoDTO;

public class ClientePresenter {

    public static ClienteDTO toDTO(Cliente cliente) {
        String loginOfuscado = ofuscarLogin(cliente.getLogin().getLogin());

        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTipoUsuario(),
                loginOfuscado,
                new EnderecoDTO(cliente.getEndereco().getBairro(), cliente.getEndereco().getCidade(), cliente.getEndereco().getEstado())
        );
    }

    private static String ofuscarLogin(String login) {
        if (login == null || login.length() < 3) {
            return "***";
        }

        return login.charAt(0) + "***" + login.charAt(login.length() - 1);
    }

}
