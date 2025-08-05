package br.com.fiap.foodtech.infrastructure.storage;

import br.com.fiap.foodtech.core.dto.*;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;
import br.com.fiap.foodtech.infrastructure.persistence.entities.*;
import br.com.fiap.foodtech.infrastructure.persistence.repositories.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MySQLDataStorageSource implements IDataStorageSource {

    private final ClienteJpaRepository clienteRepository;
    private final GestorJpaRepository gestorRepository;
    private final LoginJpaRepository loginRepository;
    private final RestauranteJpaRepository restauranteRepository;
    private final ItemCardapioJpaRepository itemCardapioRepository;

    public MySQLDataStorageSource(
            ClienteJpaRepository clienteRepository,
            GestorJpaRepository gestorRepository,
            LoginJpaRepository loginRepository,
            RestauranteJpaRepository restauranteRepository,
            ItemCardapioJpaRepository itemCardapioRepository) {
        this.clienteRepository = clienteRepository;
        this.gestorRepository = gestorRepository;
        this.loginRepository = loginRepository;
        this.restauranteRepository = restauranteRepository;
        this.itemCardapioRepository = itemCardapioRepository;
    }



    @Override
    public ClienteDataDTO obterClientePorId(Long id) {
        var clienteEntity = clienteRepository.findByIdWithDetails(id)
                .orElse(null);

        if (clienteEntity == null) {
            return null;
        }

        return mapToClienteDataDTO(clienteEntity);
    }

    @Override
    public ClienteDataDTO obterClientePorEmail(String email) {
        var clienteEntity = clienteRepository.findByEmailWithDetails(email)
                .orElse(null);

        if (clienteEntity == null) {
            return null;
        }

        return mapToClienteDataDTO(clienteEntity);
    }

    @Override
    public ClienteDataDTO incluirCliente(NovoClienteDTO novoCliente) {

        var clienteEntity = new ClienteEntity();
        clienteEntity.setNome(novoCliente.nome());
        clienteEntity.setEmail(novoCliente.email());
        clienteEntity.setTipoUsuario("CLIENTE");


        var loginEntity = new LoginEntity();
        loginEntity.setLogin(novoCliente.login());
        loginEntity.setSenha(novoCliente.senha());
        clienteEntity.setLogin(loginEntity);


        var enderecoEntity = new EnderecoEntity();
        enderecoEntity.setLogradouro(novoCliente.logradouro());
        enderecoEntity.setNumero(novoCliente.numero());
        enderecoEntity.setBairro(novoCliente.bairro());
        enderecoEntity.setCidade(novoCliente.cidade());
        enderecoEntity.setEstado(novoCliente.estado());
        enderecoEntity.setCep(novoCliente.cep());
        clienteEntity.setEndereco(enderecoEntity);


        var clienteSalvo = clienteRepository.save(clienteEntity);

        return mapToClienteDataDTO(clienteSalvo);
    }

    @Override
    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }



    @Override
    public GestorDataDTO obterGestorPorId(Long id) {
        var gestorEntity = gestorRepository.findByIdWithDetails(id)
                .orElse(null);

        if (gestorEntity == null) {
            return null;
        }

        return mapToGestorDataDTO(gestorEntity);
    }

    @Override
    public GestorDataDTO obterGestorPorEmail(String email) {
        var gestorEntity = gestorRepository.findByEmailWithDetails(email)
                .orElse(null);

        if (gestorEntity == null) {
            return null;
        }

        return mapToGestorDataDTO(gestorEntity);
    }

    @Override
    public GestorDataDTO incluirGestor(NovoGestorDTO novoGestor) {
        var gestorEntity = new GestorEntity();
        gestorEntity.setNome(novoGestor.nome());
        gestorEntity.setEmail(novoGestor.email());
        gestorEntity.setTipoUsuario("DONODERESTAURANTE");


        var loginEntity = new LoginEntity();
        loginEntity.setLogin(novoGestor.login());
        loginEntity.setSenha(novoGestor.senha());
        gestorEntity.setLogin(loginEntity);


        var enderecoEntity = new EnderecoEntity();
        enderecoEntity.setLogradouro(novoGestor.logradouro());
        enderecoEntity.setNumero(novoGestor.numero());
        enderecoEntity.setBairro(novoGestor.bairro());
        enderecoEntity.setCidade(novoGestor.cidade());
        enderecoEntity.setEstado(novoGestor.estado());
        enderecoEntity.setCep(novoGestor.cep());
        gestorEntity.setEndereco(enderecoEntity);

        var gestorSalvo = gestorRepository.save(gestorEntity);
        return mapToGestorDataDTO(gestorSalvo);
    }

    @Override
    public void deletarGestor(Long id) {
        gestorRepository.deleteById(id);
    }



    @Override
    public RestauranteDataDTO obterRestaurantePorId(Long id) {
        var restauranteEntity = restauranteRepository.findByIdWithDetails(id)
                .orElse(null);

        if (restauranteEntity == null) {
            return null;
        }

        return mapToRestauranteDataDTO(restauranteEntity);
    }

    @Override
    public List<RestauranteDataDTO> obterTodosRestaurantes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var restaurantes = restauranteRepository.findAll(pageable);

        return restaurantes.getContent().stream()
                .map(this::mapToRestauranteDataDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RestauranteDataDTO incluirRestaurante(NovoRestauranteDTO novoRestaurante) {
        var restauranteEntity = new RestauranteEntity();
        restauranteEntity.setNome(novoRestaurante.nome());
        restauranteEntity.setTipoCozinha(novoRestaurante.tipoCozinha());
        restauranteEntity.setHorarioAbertura(novoRestaurante.horarioAbertura());
        restauranteEntity.setHorarioFechamento(novoRestaurante.horarioFechamento());


        var gestorEntity = gestorRepository.findById(novoRestaurante.idGestor())
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado"));
        restauranteEntity.setGestor(gestorEntity);


        var enderecoEntity = new EnderecoEntity();
        enderecoEntity.setLogradouro(novoRestaurante.logradouro());
        enderecoEntity.setNumero(novoRestaurante.numero());
        enderecoEntity.setBairro(novoRestaurante.bairro());
        enderecoEntity.setCidade(novoRestaurante.cidade());
        enderecoEntity.setEstado(novoRestaurante.estado());
        enderecoEntity.setCep(novoRestaurante.cep());
        restauranteEntity.setEndereco(enderecoEntity);

        var restauranteSalvo = restauranteRepository.save(restauranteEntity);
        return mapToRestauranteDataDTO(restauranteSalvo);
    }

    @Override
    public RestauranteDataDTO atualizarRestaurante(Long id, NovoRestauranteDTO restauranteAtualizado) {
        var restauranteEntity = restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        restauranteEntity.setNome(restauranteAtualizado.nome());
        restauranteEntity.setTipoCozinha(restauranteAtualizado.tipoCozinha());
        restauranteEntity.setHorarioAbertura(restauranteAtualizado.horarioAbertura());
        restauranteEntity.setHorarioFechamento(restauranteAtualizado.horarioFechamento());


        if (restauranteEntity.getEndereco() != null) {
            var endereco = restauranteEntity.getEndereco();
            endereco.setLogradouro(restauranteAtualizado.logradouro());
            endereco.setNumero(restauranteAtualizado.numero());
            endereco.setBairro(restauranteAtualizado.bairro());
            endereco.setCidade(restauranteAtualizado.cidade());
            endereco.setEstado(restauranteAtualizado.estado());
            endereco.setCep(restauranteAtualizado.cep());
        }

        var restauranteSalvo = restauranteRepository.save(restauranteEntity);
        return mapToRestauranteDataDTO(restauranteSalvo);
    }

    @Override
    public void deletarRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }



    @Override
    public ItemCardapioDataDTO obterItemCardapioPorId(Long id) {
        var itemEntity = itemCardapioRepository.findById(id)
                .orElse(null);

        if (itemEntity == null) {
            return null;
        }

        return mapToItemCardapioDataDTO(itemEntity);
    }

    @Override
    public List<ItemCardapioDataDTO> obterTodosItensCardapio(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var itens = itemCardapioRepository.findAll(pageable);

        return itens.getContent().stream()
                .map(this::mapToItemCardapioDataDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ItemCardapioDataDTO incluirItemCardapio(NovoItemCardapioDTO novoItem) {
        var itemEntity = new ItemCardapioEntity();
        itemEntity.setNome(novoItem.nome());
        itemEntity.setDescricao(novoItem.descricao());
        itemEntity.setPreco(novoItem.preco());
        itemEntity.setDisponibilidadeRestaurante(novoItem.disponibilidadeRestaurante());
        itemEntity.setCaminhoFoto(novoItem.caminhoFoto());

        var itemSalvo = itemCardapioRepository.save(itemEntity);
        return mapToItemCardapioDataDTO(itemSalvo);
    }

    @Override
    public ItemCardapioDataDTO atualizarItemCardapio(Long id, NovoItemCardapioDTO itemAtualizado) {
        var itemEntity = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado"));

        itemEntity.setNome(itemAtualizado.nome());
        itemEntity.setDescricao(itemAtualizado.descricao());
        itemEntity.setPreco(itemAtualizado.preco());
        itemEntity.setDisponibilidadeRestaurante(itemAtualizado.disponibilidadeRestaurante());
        itemEntity.setCaminhoFoto(itemAtualizado.caminhoFoto());

        var itemSalvo = itemCardapioRepository.save(itemEntity);
        return mapToItemCardapioDataDTO(itemSalvo);
    }

    @Override
    public void deletarItemCardapio(Long id) {
        itemCardapioRepository.deleteById(id);
    }



    @Override
    public LoginDataDTO obterLoginPorLogin(String login) {
        var loginEntity = loginRepository.findByLogin(login)
                .orElse(null);

        if (loginEntity == null) {
            return null;
        }

        return mapToLoginDataDTO(loginEntity);
    }

    @Override
    public void atualizarLogin(Long id, String novaSenha) {
        var loginEntity = loginRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Login não encontrado"));

        loginEntity.setSenha(novaSenha);
        loginRepository.save(loginEntity);
    }



    private ClienteDataDTO mapToClienteDataDTO(ClienteEntity entity) {
        return new ClienteDataDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTipoUsuario(),
                mapToLoginDataDTO(entity.getLogin()),
                mapToEnderecoDataDTO(entity.getEndereco())
        );
    }

    private GestorDataDTO mapToGestorDataDTO(GestorEntity entity) {
        return new GestorDataDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTipoUsuario(),
                mapToLoginDataDTO(entity.getLogin()),
                mapToEnderecoDataDTO(entity.getEndereco())
        );
    }

    private RestauranteDataDTO mapToRestauranteDataDTO(RestauranteEntity entity) {
        return new RestauranteDataDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTipoCozinha(),
                entity.getHorarioAbertura(),
                entity.getHorarioFechamento(),
                mapToGestorDataDTO(entity.getGestor()),
                mapToEnderecoDataDTO(entity.getEndereco())
        );
    }

    private ItemCardapioDataDTO mapToItemCardapioDataDTO(ItemCardapioEntity entity) {
        return new ItemCardapioDataDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getDisponibilidadeRestaurante(),
                entity.getCaminhoFoto()
        );
    }

    private LoginDataDTO mapToLoginDataDTO(LoginEntity entity) {
        if (entity == null) return null;
        return new LoginDataDTO(
                entity.getId(),
                entity.getLogin(),
                entity.getSenha()
        );
    }

    private EnderecoDataDTO mapToEnderecoDataDTO(EnderecoEntity entity) {
        if (entity == null) return null;
        return new EnderecoDataDTO(
                entity.getId(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getCep()
        );
    }
}