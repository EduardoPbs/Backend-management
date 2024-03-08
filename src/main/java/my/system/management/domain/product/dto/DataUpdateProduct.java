package my.system.management.domain.product.dto;

import jakarta.validation.constraints.NotNull;
import my.system.management.domain.enums.Category;

import java.math.BigDecimal;

public record DataUpdateProduct(
        @NotNull
        String id,
        String name,
        BigDecimal value,
        Category category,
        Integer stock
) {
}
