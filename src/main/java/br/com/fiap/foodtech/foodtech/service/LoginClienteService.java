package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.dto.loginDTO;
import br.com.fiap.foodtech.foodtech.dto.SenhaDTO;
import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Usuario;
import br.com.fiap.foodtech.foodtech.repositories.ClienteRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import br.com.fiap.foodtech.foodtech.service.exceptions.UnauthorizedException;
import br.com.fiap.foodtech.foodtech.validation.ClienteValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class LoginClienteService implements LoginService {

//    private final LoginRepository loginRepository;
    private final ClienteValidator clienteValidator;
    private final ClienteRepository clienteRepository;

    public LoginClienteService(ClienteValidator clienteValidator,
                               ClienteRepository clienteRepository) {
//        this.loginRepository = loginRepository;
        this.clienteValidator = clienteValidator;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void validarLogin(loginDTO loginDTO) {
        boolean existelogin = clienteRepository.existsClienteByLogin(loginDTO.login());
        if (!existelogin) {
            throw new ResourceNotFoundException ("Cliente não encontrado");
        }
        Cliente cliente = this.clienteRepository.findByLogin(loginDTO.login());
        if (!cliente.equals(loginDTO.senha())) {
            throw new UnauthorizedException("Senha incorreta .");
        }
    }

    @Override
    public void UpdateSenha(SenhaDTO senhadto) {
        boolean existelogin = clienteRepository.existsClienteByLogin(senhadto.login());
        if (!existelogin) {
            throw new ResourceNotFoundException ("Cliente não cadastrado");
        }
        Cliente cliente = clienteRepository.findByLogin(senhadto.login());
        if (!senhadto.senhaAtual().equals(cliente.getSenha())) {
            throw new UnauthorizedException ("Senha atual não é a senha cadastrada");
        }
        if (Objects.equals(senhadto.senhaAtual(), senhadto.novaSenha())) {
            throw new UnauthorizedException ("A nova senha não pode ser igual a senha atual");
        }
        cliente.setSenha(senhadto.novaSenha());
        cliente.setDataUltimaAlteracao(LocalDateTime.now());
        clienteRepository.save(cliente);
    }
}
