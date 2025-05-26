package br.com.fiap.foodtech.foodtech.dto;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) {
}
