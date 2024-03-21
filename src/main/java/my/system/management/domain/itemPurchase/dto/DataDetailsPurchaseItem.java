package my.system.management.domain.itemPurchase.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import my.system.management.domain.itemPurchase.model.ItemPurchase;
import my.system.management.domain.orderItem.model.OrderItem;
import my.system.management.domain.product.dto.DataMinDetailsProduct;

import java.math.BigDecimal;

public record DataDetailsPurchaseItem(
        String id,
        @JsonProperty("unity_value")
        BigDecimal unityValue,
        Integer quantity,
        DataMinDetailsProduct product
) {
    public DataDetailsPurchaseItem(ItemPurchase item) {
        this(
                item.getId(),
                item.getUnityValue(),
                item.getQuantity(),
                new DataMinDetailsProduct(item.getProduct())
        );
    }
}
