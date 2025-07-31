package br.com.fiap.foodtech.core.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Endereco {
    private Long id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public static Endereco create(String logradouro, String numero, String bairro,
                                  String cidade, String estado, String cep) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);
        return endereco;
    }

    public static Endereco create(Long id, String logradouro, String numero, String bairro,
                                  String cidade, String estado, String cep) {
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);
        return endereco;
    }

    private void setId(Long id) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero");
        }
        this.id = id;
    }

    private void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    private void setNumero(String numero) {
        validateNumero(numero);
        this.numero = numero;
    }

    private void setBairro(String bairro) {
        this.bairro = bairro;
    }

    private void setCidade(String cidade) {
        validateCidade(cidade);
        this.cidade = cidade;
    }

    private void setEstado(String estado) {
        validateEstado(estado);
        this.estado = estado;
    }

    private void setCep(String cep) {
        validateCep(cep);
        this.cep = cep;
    }

    private static void validateNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("Número é obrigatório");
        }
    }

    private static void validateCidade(String cidade) {
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }
    }

    private static void validateEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("Estado é obrigatório");
        }
    }

    private static void validateCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }
        String cepLimpo = cep.replaceAll("[^0-9]", "");
        if (cepLimpo.length() != 8) {
            throw new IllegalArgumentException("CEP deve ter 8 dígitos");
        }
    }
}