package br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers;

import br.com.fiap.foodtech.foodtech.core.dtos.LoginDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.LoginDataDTO;
import br.com.fiap.foodtech.foodtech.core.dtos.NovoLoginDTO;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.LoginEntity;

public abstract class LoginMapper {

    public static LoginDataDTO toDTO(LoginEntity login) {
        return new LoginDataDTO(login.getId(), login.getLogin());
    }

    public static LoginEntity toEntity(NovoLoginDTO dto) {
        return new LoginEntity(dto.login(), dto.senha());
    }

    public static LoginEntity toEntity(LoginDataDTO dto) {
        return new LoginEntity(dto.id(), dto.login());
    }

    public static LoginEntity toEntity(LoginDTO dto) {
        return new LoginEntity(dto.login(), dto.senha());
    }

}
