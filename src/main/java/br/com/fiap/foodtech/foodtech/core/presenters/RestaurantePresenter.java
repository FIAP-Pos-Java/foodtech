package br.com.fiap.foodtech.foodtech.core.presenters;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.dtos.EnderecoDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.RestauranteDTO;

public class RestaurantePresenter {

    public static RestauranteDTO toDTO(Restaurante restaurante) {

        return new RestauranteDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                new EnderecoDTO(
                        restaurante.getEndereco().getBairro(),
                        restaurante.getEndereco().getCidade(),
                        restaurante.getEndereco().getEstado()
                ),
                restaurante.getGestor().getId(),
                restaurante.getGestor().getNome()
        );
    }
}
