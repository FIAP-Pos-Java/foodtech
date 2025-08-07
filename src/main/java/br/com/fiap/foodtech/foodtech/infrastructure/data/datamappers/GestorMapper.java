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
                                dto.novoLogin().login(),
                                dto.novoLogin().senha()
                        )
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

    public static GestorEntity toEntity(GestorDataDTO dto) {
        return new GestorEntity(
                dto.nome(),
                dto.email(),
                dto.tipoUsuario(),
                LoginMapper.toEntity(
                        new LoginDataDTO(
                                dto.loginData().id(),
                                dto.loginData().login()
                        )
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
