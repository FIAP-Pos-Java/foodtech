package br.com.fiap.foodtech.foodtech.core.dtos;

import java.util.List;

public record Pagina<T>(List<T> content, int totalElements) {}
