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
                                dto.novoLogin().login(),
                                dto.novoLogin().senha())
                ),
                EnderecoMapper.toEntity(
                        new NovoEnderecoDTO(
                                dto.novoEndereco().logradouro(),
                                dto.novoEndereco().numero(),
                                dto.novoEndereco().bairro(),
                                dto.novoEndereco().cidade(),
                                dto.novoEndereco().estado(),
                                dto.novoEndereco().cep()
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
                                dto.loginData().id(),
                                dto.loginData().login())
                ),
                EnderecoMapper.toEntity(
                        new EnderecoDataDTO(
                                dto.enderecoData().id(),
                                dto.enderecoData().logradouro(),
                                dto.enderecoData().numero(),
                                dto.enderecoData().bairro(),
                                dto.enderecoData().cidade(),
                                dto.enderecoData().estado(),
                                dto.enderecoData().cep()
                        )
                )
        );
    }
}
