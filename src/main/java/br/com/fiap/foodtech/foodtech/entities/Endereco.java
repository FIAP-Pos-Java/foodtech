package br.com.fiap.foodtech.foodtech.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Endereco {
    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
