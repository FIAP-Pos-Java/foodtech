package br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers;

import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.GestorEntity;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.ItemCardapioEntity;

public abstract class ItemCardapioMapper {

    public static ItemCardapioDataDTO toDTO(ItemCardapioEntity entity) {
        return new ItemCardapioDataDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getDisponibilidadeRestaurante(),
                entity.getCaminhoFoto()
        );
    }

    public static ItemCardapioEntity toEntity(NovoItemCardapioDTO dto) {
        return new ItemCardapioEntity(
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.disponibilidadeRestaurante(),
                dto.caminhoFoto()
        );
    }

    public static ItemCardapioEntity toEntity(ItemCardapioDataDTO dto) {
        return new ItemCardapioEntity(
                dto.id(),
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.disponibilidadeRestaurante(),
                dto.caminhoFoto()
        );
    }

}
