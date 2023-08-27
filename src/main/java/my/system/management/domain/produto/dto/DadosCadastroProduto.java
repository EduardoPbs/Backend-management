package my.system.management.domain.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import my.system.management.domain.enums.Categoria;

import java.math.BigDecimal;

public record DadosCadastroProduto(

        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "^\\d+$")
        String codigo,

        @NotNull
        BigDecimal valor,

        @NotNull // @NotBlank -> apenas para tipo String
        Categoria categoria,

        @NotNull
        Integer quantidade
) {}
