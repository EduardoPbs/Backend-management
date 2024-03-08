package my.system.management.domain.product.dto;

import jakarta.validation.constraints.NotNull;
import my.system.management.domain.enums.Category;

import java.math.BigDecimal;

public record DataUpdateProduct(
        String code,
        String name,
        BigDecimal value,
        Category category,
        Integer stock,
        boolean active
) {
}
