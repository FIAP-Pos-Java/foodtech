package br.com.fiap.foodtech.foodtech.infrastructure.data.repositories;

import br.com.fiap.foodtech.foodtech.core.dtos.*;
import br.com.fiap.foodtech.foodtech.core.interfaces.DataSource;
import br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers.ClienteMapper;
import br.com.fiap.foodtech.foodtech.infrastructure.data.datamappers.GestorMapper;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.ClienteEntity;
import br.com.fiap.foodtech.foodtech.infrastructure.data.entities.GestorEntity;
import br.com.fiap.foodtech.foodtech.infrastructure.data.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataRepository implements DataSource {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    GestorRepository gestorRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    ItemCardapioRepository itemCardapioRepository;

    @Autowired
    RestauranteRepository restauranteRepository;

    @Override
    public ClienteDataDTO obterClientePorId(Long id) {
        ClienteEntity cliente = this.clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        return ClienteMapper.toDTO(cliente);
    }

    @Override
    public ClienteDataDTO obterClientePorEmail(String email) {
        ClienteEntity cliente = this.clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        return ClienteMapper.toDTO(cliente);
    }

    @Override
    public ClienteDataDTO incluirCliente(NovoClienteDTO novoCliente) {
        ClienteEntity cliente = this.clienteRepository.save(ClienteMapper.toEntity(novoCliente));
        return ClienteMapper.toDTO(cliente);
    }

    @Override
    public void deletarCliente(Long id) {
        this.clienteRepository.deleteById(id);
    }

    @Override
    public GestorDataDTO obterGestorPorId(Long id) {
        GestorEntity gestor = this.gestorRepository.findById(id).orElse(null);
        if (gestor == null) {
            throw new ResourceNotFoundException("Gestor não encontrado");
        }
        return GestorMapper.toDTO(gestor);
    }

    @Override
    public GestorDataDTO obterGestorPorEmail(String email) {
        GestorEntity gestor = this.gestorRepository.findByEmail(email);
        if (gestor == null) {
            throw new ResourceNotFoundException("Gestor não encontrado");
        }
        return GestorMapper.toDTO(gestor);
    }

    @Override
    public GestorDataDTO incluirGestor(NovoGestorDTO novoGestor) {
        GestorEntity gestor = this.gestorRepository.save(GestorMapper.toEntity(novoGestor));
        return GestorMapper.toDTO(gestor);
    }

    @Override
    public void deletarGestor(Long id) {
        this.gestorRepository.deleteById(id);
    }

    @Override
    public ItemCardapioDataDTO obterItemCardapioPorId(Long id) {
        return null;
    }

    @Override
    public List<ItemCardapioDataDTO> obterTodosItensCardapio(int page, int size) {
        return List.of();
    }

    @Override
    public ItemCardapioDataDTO incluirItemCardapio(NovoItemCardapioDTO novoItem) {
        return null;
    }

    @Override
    public ItemCardapioDataDTO atualizarItemCardapio(Long id, NovoItemCardapioDTO itemAtualizado) {
        return null;
    }

    @Override
    public void deletarItemCardapio(Long id) {

    }

    @Override
    public RestauranteDataDTO obterRestaurantePorId(Long id) {
        return null;
    }

    @Override
    public List<RestauranteDataDTO> obterTodosRestaurantes(int page, int size) {
        return List.of();
    }

    @Override
    public RestauranteDataDTO incluirRestaurante(NovoRestauranteDTO novoRestaurante) {
        return null;
    }

    @Override
    public RestauranteDataDTO atualizarRestaurante(Long id, NovoRestauranteDTO restauranteAtualizado) {
        return null;
    }

    @Override
    public void deletarRestaurante(Long id) {

    }

    @Override
    public LoginDataDTO obterLoginPorLogin(String login) {
        return null;
    }

    @Override
    public void atualizarLogin(Long id, String novaSenha) {

    }
}
