package my.system.management.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import my.system.management.domain.enums.Category;
import my.system.management.domain.product.model.Product;

import java.math.BigDecimal;

public record DataListProduct(
        String id,
        String name,
        String code,
        BigDecimal value,
        Category category,
        Integer stock,
        @JsonProperty("active")
        Boolean isActive) {

    public DataListProduct(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getValue(),
                product.getCategory(),
                product.getStock(),
                product.isActive()
        );
    }
}
