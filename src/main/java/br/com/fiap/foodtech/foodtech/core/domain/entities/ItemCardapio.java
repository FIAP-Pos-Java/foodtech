package br.com.fiap.foodtech.foodtech.core.domain.entities;

import java.math.BigDecimal;

public class ItemCardapio {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean disponibilidadeRestaurante;
    private String caminhoFoto;

    public ItemCardapio(Long id, String nome, String descricao, BigDecimal preco, Boolean disponibilidadeRestaurante, String caminhoFoto) {
        setId(id);
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        setDisponibilidadeRestaurante(disponibilidadeRestaurante);
        setCaminhoFoto(caminhoFoto);
    }

    public ItemCardapio(String nome, String descricao, BigDecimal preco, Boolean disponibilidadeRestaurante, String caminhoFoto) {
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        setDisponibilidadeRestaurante(disponibilidadeRestaurante);
        setCaminhoFoto(caminhoFoto);
    }

    private void setId(Long id) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero");
        }
        this.id = id;
    }

    private void setNome(String nome) {
        validateNome(nome);
        this.nome = nome;
    }

    private void setDescricao(String descricao) {
        validateDescricao(descricao);
        this.descricao = descricao;
    }

    private void setPreco(BigDecimal preco) {
        validatePreco(preco);
        this.preco = preco;
    }

    private void setDisponibilidadeRestaurante(Boolean disponibilidadeRestaurante) {
        if (disponibilidadeRestaurante == null) {
            throw new IllegalArgumentException("Disponibilidade no restaurante é obrigatória");
        }
        this.disponibilidadeRestaurante = disponibilidadeRestaurante;
    }

    private void setCaminhoFoto(String caminhoFoto) {
        validateCaminhoFoto(caminhoFoto);
        this.caminhoFoto = caminhoFoto;
    }

    private static void validateNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (nome.length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
    }

    private static void validateDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição é obrigatória");
        }
        if (descricao.length() < 5) {
            throw new IllegalArgumentException("Descrição deve ter pelo menos 5 caracteres");
        }
    }

    private static void validatePreco(BigDecimal preco) {
        if (preco == null) {
            throw new IllegalArgumentException("Preço é obrigatório");
        }
        if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
    }

    private static void validateCaminhoFoto(String caminhoFoto) {
        if (caminhoFoto == null || caminhoFoto.trim().isEmpty()) {
            throw new IllegalArgumentException("Caminho da foto é obrigatório");
        }
    }

    public void atualizarPreco(BigDecimal novoPreco) {
        validatePreco(novoPreco);
        this.preco = novoPreco;
    }

    public void alterarDisponibilidade(Boolean novaDisponibilidade) {
        if (novaDisponibilidade == null) {
            throw new IllegalArgumentException("Disponibilidade não pode ser nula");
        }
        this.disponibilidadeRestaurante = novaDisponibilidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Boolean getDisponibilidadeRestaurante() {
        return disponibilidadeRestaurante;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

}
