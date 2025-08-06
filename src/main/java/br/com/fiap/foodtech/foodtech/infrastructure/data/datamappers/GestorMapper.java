package br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers;

import br.com.fiap.foodtech.foodtech.core.dtos.EnderecoDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.GestorDataDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.LoginDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoGestorDTO;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.GestorEntity;

public abstract class GestorMapper {

    public static GestorDataDTO toDTO(GestorEntity entity) {
        return new GestorDataDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTipoUsuario(),
                LoginMapper.toDTO(entity.getLogin()),
                EnderecoMapper.toDTO(entity.getEndereco())
        );
    }

    public static GestorEntity toEntity(NovoGestorDTO dto) {
        return new GestorEntity(
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                LoginMapper.toEntity(new LoginDTO(dto.login(), dto.senha())),
                EnderecoMapper.toEntity(new EnderecoDTO(dto.logradouro(), dto.numero(), dto.bairro(), dto.cidade(), dto.estado(), dto.cep()))
        );
    }
}
