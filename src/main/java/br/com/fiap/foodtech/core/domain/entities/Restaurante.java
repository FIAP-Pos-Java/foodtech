package br.com.fiap.foodtech.core.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.time.LocalTime;

@Getter
@EqualsAndHashCode
public class Restaurante {
    private Long id;
    private String nome;
    private String tipoCozinha;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private Gestor gestor;
    private Endereco endereco;

    public static Restaurante create(String nome, String tipoCozinha, LocalTime horarioAbertura,
                                     LocalTime horarioFechamento, Gestor gestor, Endereco endereco) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(nome);
        restaurante.setTipoCozinha(tipoCozinha);
        restaurante.setHorarioAbertura(horarioAbertura);
        restaurante.setHorarioFechamento(horarioFechamento);
        restaurante.setGestor(gestor);
        restaurante.setEndereco(endereco);
        return restaurante;
    }

    public static Restaurante create(Long id, String nome, String tipoCozinha, LocalTime horarioAbertura,
                                     LocalTime horarioFechamento, Gestor gestor, Endereco endereco) {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(id);
        restaurante.setNome(nome);
        restaurante.setTipoCozinha(tipoCozinha);
        restaurante.setHorarioAbertura(horarioAbertura);
        restaurante.setHorarioFechamento(horarioFechamento);
        restaurante.setGestor(gestor);
        restaurante.setEndereco(endereco);
        return restaurante;
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

    private void setTipoCozinha(String tipoCozinha) {
        validateTipoCozinha(tipoCozinha);
        this.tipoCozinha = tipoCozinha;
    }

    private void setHorarioAbertura(LocalTime horarioAbertura) {
        validateHorarioAbertura(horarioAbertura);
        this.horarioAbertura = horarioAbertura;
    }

    private void setHorarioFechamento(LocalTime horarioFechamento) {
        validateHorarioFechamento(horarioFechamento);
        this.horarioFechamento = horarioFechamento;
    }

    private void setGestor(Gestor gestor) {
        if (gestor == null) {
            throw new IllegalArgumentException("Gestor é obrigatório");
        }
        this.gestor = gestor;
    }

    private void setEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço é obrigatório");
        }
        this.endereco = endereco;
    }

    private static void validateNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do restaurante é obrigatório");
        }
        if (nome.length() < 2) {
            throw new IllegalArgumentException("Nome do restaurante deve ter pelo menos 2 caracteres");
        }
    }

    private static void validateTipoCozinha(String tipoCozinha) {
        if (tipoCozinha == null || tipoCozinha.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de cozinha é obrigatório");
        }
    }

    private static void validateHorarioAbertura(LocalTime horarioAbertura) {
        if (horarioAbertura == null) {
            throw new IllegalArgumentException("Horário de abertura é obrigatório");
        }
    }

    private static void validateHorarioFechamento(LocalTime horarioFechamento) {
        if (horarioFechamento == null) {
            throw new IllegalArgumentException("Horário de fechamento é obrigatório");
        }
    }

    private void validateHorarios() {
        if (horarioAbertura != null && horarioFechamento != null) {
            if (horarioAbertura.equals(horarioFechamento)) {
                throw new IllegalArgumentException("Horário de abertura e fechamento não podem ser iguais");
            }
            if (horarioAbertura.isAfter(horarioFechamento)) {
                throw new IllegalArgumentException("Horário de abertura não pode ser após o horário de fechamento");
            }
        }
    }
}