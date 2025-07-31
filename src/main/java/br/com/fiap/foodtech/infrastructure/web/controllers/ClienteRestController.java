package br.com.fiap.foodtech.infrastructure.web.controllers;

import br.com.fiap.foodtech.core.adapters.controllers.ClienteController;
import br.com.fiap.foodtech.core.dto.ClienteDTO;
import br.com.fiap.foodtech.core.dto.NovoClienteDTO;
import br.com.fiap.foodtech.core.exceptions.ClienteJaExistenteException;
import br.com.fiap.foodtech.core.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.foodtech.core.interfaces.IDataStorageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * Controller REST que atua como "Adapter de Interface".
 * Esta classe recebe requisições HTTP, chama o controller do domínio,
 * e retorna respostas HTTP adequadas.
 *
 * Note que este controller NÃO contém lógica de negócio!
 * Ele apenas:
 * 1. Recebe a requisição HTTP
 * 2. Chama o controller do domínio
 * 3. Converte exceções do domínio em códigos HTTP
 * 4. Retorna a resposta
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    private final IDataStorageSource dataStorageSource;

    public ClienteRestController(IDataStorageSource dataStorageSource) {
        this.dataStorageSource = dataStorageSource;
    }

    /**
     * Endpoint para cadastrar um novo cliente.
     *
     * Fluxo da requisição:
     * HTTP Request -> ClienteRestController -> ClienteController (domínio)
     * -> CadastrarClienteUseCase -> ClienteGateway -> MySQLDataStorageSource -> Banco
     */
    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody NovoClienteDTO novoClienteDTO) {
        try {
            // Cria uma instância do controller do domínio
            var clienteController = ClienteController.create(dataStorageSource);

            // Chama o método de cadastro do domínio
            ClienteDTO clienteCadastrado = clienteController.cadastrar(novoClienteDTO);

            // Retorna resposta de sucesso com o cliente criado
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteCadastrado);

        } catch (ClienteJaExistenteException e) {
            // Converte exceção do domínio em resposta HTTP 409 (Conflict)
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("Cliente já existe", e.getMessage()));

        } catch (IllegalArgumentException e) {
            // Converte exceção de validação em resposta HTTP 400 (Bad Request)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Dados inválidos", e.getMessage()));

        } catch (Exception e) {
            // Trata qualquer erro inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    /**
     * Endpoint para buscar cliente por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        try {
            var clienteController = ClienteController.create(dataStorageSource);
            ClienteDTO cliente = clienteController.buscarPorId(id);

            return ResponseEntity.ok(cliente);

        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Cliente não encontrado", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Erro interno", "Erro inesperado no servidor"));
        }
    }

    /**
     * Classe interna para padronizar respostas de erro.
     */
    private record ErrorResponse(String tipo, String mensagem) {}
}