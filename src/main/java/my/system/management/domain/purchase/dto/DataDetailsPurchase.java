package my.system.management.domain.purchase.dto;

import my.system.management.domain.itemPurchase.model.ItemPurchase;
import my.system.management.domain.purchase.model.Purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DataDetailsPurchase(
        String id,
        List<ItemPurchase> itens,
        LocalDateTime data,
        BigDecimal total
) {
    public DataDetailsPurchase(Purchase order) {
        this(order.getId(), order.getItems(), order.getDate(), order.getTotal());
    }
}
