package br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers;

import br.com.fiap.foodtech.foodtech.core.dtos.*;
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
                LoginMapper.toEntity(
                        new NovoLoginDTO(
                                dto.login().login(),
                                dto.login().senha())
                ),
                EnderecoMapper.toEntity(
                        new NovoEnderecoDTO(
                                dto.endereco().logradouro(),
                                dto.endereco().numero(),
                                dto.endereco().bairro(),
                                dto.endereco().cidade(),
                                dto.endereco().estado(),
                                dto.endereco().cep()
                        )
                )
        );
    }

    public static ClienteEntity toEntity(ClienteDataDTO dto) {
        return new ClienteEntity(
                dto.id(),
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                LoginMapper.toEntity(
                        new LoginDataDTO(
                                dto.login().id(),
                                dto.login().login())
                ),
                EnderecoMapper.toEntity(
                        new EnderecoDataDTO(
                                dto.endereco().id(),
                                dto.endereco().logradouro(),
                                dto.endereco().numero(),
                                dto.endereco().bairro(),
                                dto.endereco().cidade(),
                                dto.endereco().estado(),
                                dto.endereco().cep()
                        )
                )
        );
    }
}
