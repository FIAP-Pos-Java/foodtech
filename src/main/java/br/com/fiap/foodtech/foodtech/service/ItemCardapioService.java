package br.com.fiap.foodtech.foodtech.service;

import br.com.fiap.foodtech.foodtech.dto.ItemCardapioDTO;
import br.com.fiap.foodtech.foodtech.entities.ItemCardapio;
import br.com.fiap.foodtech.foodtech.repositories.ItemCardapioRepository;
import br.com.fiap.foodtech.foodtech.service.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemCardapioService {

    private final ItemCardapioRepository itemCardapioRepository;

    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public Page<ItemCardapio> findAllItensCardapio(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.itemCardapioRepository.findAll(pageable);
    }

    public ItemCardapio findItemCardapio(Long id) {
        return this.itemCardapioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item do cardapio não encontrado. ID: " + id));
    }

    public void saveItemCardapio(ItemCardapioDTO itemCardapioDTO) {
        ItemCardapio novoItemCardapio = itemCardapioDTO.mapearItemCardapio();
        this.itemCardapioRepository.save(novoItemCardapio);
    }

    public void updateItemCardapio(Long id, ItemCardapioDTO itemCardapioDTO) {
        ItemCardapio ItemExistente = this.itemCardapioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item do cardapio não encontrado. ID: " + id));

        ItemCardapio itemAtualizado = itemCardapioDTO.mapearItemCardapio();

        itemAtualizado.setId(id);
        this.itemCardapioRepository.save(itemAtualizado);
    }

    public void deleteItemCardapio(Long id) {
        this.itemCardapioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item do cardapio não encontrado. ID: " + id));
        this.itemCardapioRepository.deleteById(id);
    }
}

