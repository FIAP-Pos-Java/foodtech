package br.com.fiap.foodtech.foodtech.core.presenters;

import br.com.fiap.foodtech.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.foodtech.core.dtos.EnderecoDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.RestauranteDTO;

public class RestaurantePresenter {

    public static RestauranteDTO toDTO(Restaurante restaurante) {
        /*EnderecoDTO enderecoDTO = new EnderecoDTO(
                restaurante.getEndereco().getLogradouro(),
                restaurante.getEndereco().getNumero(),
                restaurante.getEndereco().getBairro(),
                restaurante.getEndereco().getCidade(),
                restaurante.getEndereco().getEstado(),
                restaurante.getEndereco().getCep()
        );

        return new RestauranteDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                restaurante.getGestor().getId(),
                restaurante.getGestor().getNome(),
                enderecoDTO
        );*/
        return null;
    }

}
