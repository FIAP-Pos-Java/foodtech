package br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers;

import br.com.fiap.foodtech.foodtech.core.dtos.EnderecoDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.EnderecoDataDTO;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.EnderecoEntity;

public abstract class EnderecoMapper {

    protected static EnderecoDataDTO toDTO(EnderecoEntity endereco) {
        return new EnderecoDataDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }

    protected static EnderecoEntity toEntity(EnderecoDTO dto) {
        return new EnderecoEntity(
                dto.logradouro(),
                dto.numero(),
                dto.bairro(),
                dto.cidade(),
                dto.estado(),
                dto.cep()
        );
    }
}
