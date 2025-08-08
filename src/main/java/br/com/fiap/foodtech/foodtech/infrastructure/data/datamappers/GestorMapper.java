package br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers;

import br.com.fiap.foodtech.foodtech.core.dtos.*;
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
                LoginMapper.toEntity(
                        new NovoLoginDTO(
                                dto.login().login(),
                                dto.login().senha()
                        )
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

    public static GestorEntity toEntity(GestorDataDTO dto) {
        return new GestorEntity(
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                LoginMapper.toEntity(
                        new LoginDataDTO(
                                dto.login().id(),
                                dto.login().login()
                        )
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
