package br.com.fiap.foodtech.core.adapters.presenters;

import br.com.fiap.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.core.dto.GestorDTO;

public class GestorPresenter {

    public static GestorDTO toDTO(Gestor gestor) {

        String loginOfuscado = ofuscarLogin(gestor.getLogin().getLogin());

        return new GestorDTO(
                gestor.getId(),
                gestor.getNome(),
                gestor.getEmail(),
                gestor.getTipoUsuario(),
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