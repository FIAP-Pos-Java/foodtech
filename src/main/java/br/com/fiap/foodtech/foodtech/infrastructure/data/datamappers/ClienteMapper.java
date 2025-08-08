package br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers;

import br.com.fiap.foodtech.foodtech.core.dtos.ClienteDataDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.EnderecoDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.LoginDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoClienteDTO;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.ClienteEntity;

public abstract class ClienteMapper {

    public static ClienteDataDTO toDTO(ClienteEntity entity) {
        return new ClienteDataDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTipoUsuario(),
                LoginMapper.toDTO(entity.getLogin()),
                EnderecoMapper.toDTO(entity.getEndereco())
        );
    }

    public static ClienteEntity toEntity(NovoClienteDTO dto) {
        return new ClienteEntity(
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                LoginMapper.toEntity(new LoginDTO(dto.login(), dto.senha())),
                EnderecoMapper.toEntity(new EnderecoDTO(dto.logradouro(), dto.numero(), dto.bairro(), dto.cidade(), dto.estado(), dto.cep()))
        );
    }
}
