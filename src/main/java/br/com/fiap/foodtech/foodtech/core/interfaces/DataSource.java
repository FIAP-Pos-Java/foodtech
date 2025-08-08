package br.com.fiap.foodtech.foodtech.core.interfaces;

import br.com.fiap.foodtech.foodtech.core.dtos.*;

import java.util.List;

public interface DataSource {

    Pagina<ClienteDataDTO> obterTodosClientes(Paginacao paginacao);
    ClienteDataDTO obterClientePorId(Long id);
    ClienteDataDTO obterClientePorEmail(String email);
    ClienteDataDTO incluirCliente(NovoClienteDTO novoClienteDTO);
    ClienteDataDTO atualizarCliente(ClienteDataDTO clienteDataDTO);
    void deletarCliente(Long id);

    Pagina<GestorDataDTO> obterTodosGestores(Paginacao paginacao);
    GestorDataDTO obterGestorPorId(Long id);
    GestorDataDTO obterGestorPorEmail(String email);
    GestorDataDTO incluirGestor(NovoGestorDTO novoGestor);
    GestorDataDTO atualizarGestor(GestorDataDTO gestorDataDTO);
    void deletarGestor(Long id);

    ItemCardapioDataDTO obterItemCardapioPorId(Long id);
    List<ItemCardapioDataDTO> obterTodosItensCardapio(int page, int size);
    ItemCardapioDataDTO incluirItemCardapio(NovoItemCardapioDTO novoItem);
    ItemCardapioDataDTO atualizarItemCardapio(Long id, NovoItemCardapioDTO itemAtualizado);
    void deletarItemCardapio(Long id);

    RestauranteDataDTO obterRestaurantePorId(Long id);
    List<RestauranteDataDTO> obterTodosRestaurantes(int page, int size);
    RestauranteDataDTO incluirRestaurante(NovoRestauranteDTO novoRestaurante);
    RestauranteDataDTO atualizarRestaurante(Long id, NovoRestauranteDTO restauranteAtualizado);
    void deletarRestaurante(Long id);

    LoginDataDTO obterLoginPorLogin(String login);
    void atualizarSenha(LoginDTO loginDTO);

}
