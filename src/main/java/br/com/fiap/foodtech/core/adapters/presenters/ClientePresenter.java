package br.com.fiap.foodtech.core.adapters.presenters;

import br.com.fiap.foodtech.core.domain.entities.Cliente;
import br.com.fiap.foodtech.core.dto.ClienteDTO;

public class ClientePresenter {

    public static ClienteDTO toDTO(Cliente cliente) {
        String loginOfuscado = ofuscarLogin(cliente.getLogin().getLogin());

        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTipoUsuario(),
                loginOfuscado
        );
    }

    private static String ofuscarLogin(String login) {
        if (login == null || login.length() < 3) {
            return "***";
        }

        return login.charAt(0) + "***" + login.charAt(login.length() - 1);
    }
}