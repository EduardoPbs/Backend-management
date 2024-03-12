package my.system.management.domain.product.dto;

import my.system.management.domain.product.model.Product;

import java.math.BigDecimal;

public record DataMinDetailsProduct(String id, String name, String code, BigDecimal value) {
    public DataMinDetailsProduct(Product product) {
        this(product.getId(), product.getName(), product.getCode(), product.getValue());
    }
}
