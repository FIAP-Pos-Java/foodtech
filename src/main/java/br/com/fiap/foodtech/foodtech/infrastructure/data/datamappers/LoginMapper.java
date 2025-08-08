package br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers;

import br.com.fiap.foodtech.foodtech.core.dtos.LoginDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.LoginDataDTO;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.LoginEntity;

public abstract class LoginMapper {

    protected static LoginDataDTO toDTO(LoginEntity login) {
        return new LoginDataDTO(
                login.getId(),
                login.getLogin(),
                login.getSenha()
        );
    }

    protected static LoginEntity toEntity(LoginDTO dto) {
        return new LoginEntity(dto.login(), dto.senha());
    }

}
