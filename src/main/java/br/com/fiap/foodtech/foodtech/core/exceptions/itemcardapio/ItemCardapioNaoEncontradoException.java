package br.com.fiap.foodtech.foodtech.core.exceptions.itemcardapio;

import br.com.fiap.foodtech.foodtech.core.exceptions.ResourceNotFoundException;

public class ItemCardapioNaoEncontradoException extends ResourceNotFoundException {
    public ItemCardapioNaoEncontradoException(String message) {
        super(message);
    }
}
