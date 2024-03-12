package my.system.management.domain.orderItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import my.system.management.domain.orderItem.model.OrderItem;
import my.system.management.domain.product.dto.DataMinDetailsProduct;

import java.math.BigDecimal;

public record DataDetailsOrderItem(
        String id,
        @JsonProperty("unity_value")
        BigDecimal unityValue,
        Integer quantity,
        DataMinDetailsProduct product
) {
    public DataDetailsOrderItem(OrderItem item) {
        this(
                item.getId(),
                item.getUnityValue(),
                item.getQuantity(),
                new DataMinDetailsProduct(item.getProduct())
        );
    }
}
