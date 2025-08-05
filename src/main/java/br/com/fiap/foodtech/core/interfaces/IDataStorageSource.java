package br.com.fiap.foodtech.core.interfaces;

import br.com.fiap.foodtech.core.dto.*;

import java.util.List;


public interface IDataStorageSource {

    ClienteDataDTO obterClientePorId(Long id);
    ClienteDataDTO obterClientePorEmail(String email);
    ClienteDataDTO incluirCliente(NovoClienteDTO novoCliente);
    void deletarCliente(Long id);


    GestorDataDTO obterGestorPorId(Long id);
    GestorDataDTO obterGestorPorEmail(String email);
    GestorDataDTO incluirGestor(NovoGestorDTO novoGestor);
    void deletarGestor(Long id);


    RestauranteDataDTO obterRestaurantePorId(Long id);
    List<RestauranteDataDTO> obterTodosRestaurantes(int page, int size);
    RestauranteDataDTO incluirRestaurante(NovoRestauranteDTO novoRestaurante);
    RestauranteDataDTO atualizarRestaurante(Long id, NovoRestauranteDTO restauranteAtualizado);
    void deletarRestaurante(Long id);


    ItemCardapioDataDTO obterItemCardapioPorId(Long id);
    List<ItemCardapioDataDTO> obterTodosItensCardapio(int page, int size);
    ItemCardapioDataDTO incluirItemCardapio(NovoItemCardapioDTO novoItem);
    ItemCardapioDataDTO atualizarItemCardapio(Long id, NovoItemCardapioDTO itemAtualizado);
    void deletarItemCardapio(Long id);


    LoginDataDTO obterLoginPorLogin(String login);
    void atualizarLogin(Long id, String novaSenha);
}