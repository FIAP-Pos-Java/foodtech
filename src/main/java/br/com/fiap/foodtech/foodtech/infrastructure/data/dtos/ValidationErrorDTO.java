package br.com.fiap.foodtech.foodtech.infrastructure.data.dtos;

import java.util.List;

public record ValidationErrorDTO(List<String> errors, int status) { }
