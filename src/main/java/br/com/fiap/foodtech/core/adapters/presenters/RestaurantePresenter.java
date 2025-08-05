package br.com.fiap.foodtech.core.adapters.presenters;

import br.com.fiap.foodtech.core.domain.entities.Restaurante;
import br.com.fiap.foodtech.core.dto.EnderecoDTO;
import br.com.fiap.foodtech.core.dto.RestauranteDTO;

public class RestaurantePresenter {

    public static RestauranteDTO toDTO(Restaurante restaurante) {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
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
        );
    }
}