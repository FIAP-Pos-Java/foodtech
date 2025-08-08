package br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers;

import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.ClienteEntity;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.GestorEntity;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.RestauranteEntity;

public abstract class RestauranteMapper {

    public static RestauranteDataDTO toDTO(RestauranteEntity entity) {
        return new RestauranteDataDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTipoCozinha(),
                entity.getHorarioAbertura(),
                entity.getHorarioFechamento(),
                GestorMapper.toDTO(entity.getGestor()),
                EnderecoMapper.toDataDTO(entity.getEndereco())
        );
    }

    public static RestauranteEntity toEntity(NovoRestauranteComGestorDTO dto) {
        return new RestauranteEntity(
                dto.nome(),
                dto.tipoCozinha(),
                dto.horarioAbertura(),
                dto.horarioFechamento(),
                GestorMapper.toEntity(dto.gestor()),
                EnderecoMapper.toEntity(dto.endereco())
        );
    }

    public static RestauranteEntity toEntity(RestauranteDataDTO dto) {
        return new RestauranteEntity(
                dto.id(),
                dto.nome(),
                dto.tipoCozinha(),
                dto.horarioAbertura(),
                dto.horarioFechamento(),
                GestorMapper.toEntity(dto.gestor()),
                EnderecoMapper.toEntity(dto.endereco())
        );
    }

}
