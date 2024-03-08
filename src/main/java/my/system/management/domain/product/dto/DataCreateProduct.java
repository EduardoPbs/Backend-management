package my.system.management.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import my.system.management.domain.enums.Category;

import java.math.BigDecimal;

public record DataCreateProduct(

        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "^\\d+$")
        String code,

        @NotNull
        BigDecimal value,

        @NotNull
        Category category,

        @NotNull
        Integer stock
) {}
