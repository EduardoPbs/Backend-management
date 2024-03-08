package my.system.management.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import my.system.management.domain.enums.Category;
import my.system.management.domain.product.model.Product;

import java.math.BigDecimal;

public record DataDetailsProduct(
        String id,
        String name,
        String code,
        BigDecimal value,
        Integer stock,
        Category category,
        @JsonProperty("active")
        Boolean isActive) {
    public DataDetailsProduct(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getValue(),
                product.getStock(),
                product.getCategory(),
                product.isActive()
        );
    }
}
