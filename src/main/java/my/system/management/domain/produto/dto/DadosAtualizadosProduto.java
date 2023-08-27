package my.system.management.domain.produto.dto;

import jakarta.validation.constraints.NotNull;
import my.system.management.domain.enums.Categoria;

import java.math.BigDecimal;

public record DadosAtualizadosProduto(
        @NotNull
        String id,
        String nome,
        BigDecimal valor,
        Categoria categoria,
        Integer quantidade
) {}
