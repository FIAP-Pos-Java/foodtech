package br.com.fiap.foodtech.foodtech.core.presenters;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Gestor;
import br.com.fiap.foodtech.foodtech.core.dtos.EnderecoDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.GestorDTO;

public class GestorPresenter {

    public static GestorDTO toDTO(Gestor gestor) {

        String loginOfuscado = ofuscarLogin(gestor.getLogin().getLogin());

        return new GestorDTO(
                gestor.getId(),
                gestor.getNome(),
                gestor.getEmail(),
                gestor.getTipoUsuario(),
                loginOfuscado,
                new EnderecoDTO(gestor.getEndereco().getBairro(), gestor.getEndereco().getCidade(), gestor.getEndereco().getEstado())
        );
    }

    private static String ofuscarLogin(String login) {
        if (login == null || login.length() < 3) {
            return "***";
        }

        return login.charAt(0) + "***" + login.charAt(login.length() - 1);
    }

}
