package my.system.management.domain.purchase.dto;

import my.system.management.domain.enums.PurchaseStatus;
import my.system.management.domain.itemPurchase.dto.DataDetailsPurchaseItem;
import my.system.management.domain.itemPurchase.model.ItemPurchase;
import my.system.management.domain.purchase.model.Purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record DataDetailsPurchase(
        String id,
        List<DataDetailsPurchaseItem> items,
        LocalDateTime date,
        PurchaseStatus status,
        BigDecimal total
) {
    public DataDetailsPurchase(Purchase purchase) {
        this(
                purchase.getId(),
                generateItemsDto(purchase.getItems()),
                purchase.getDate(),
                purchase.getStatus(),
                purchase.getTotal()
        );
    }

    private static List<DataDetailsPurchaseItem> generateItemsDto(List<ItemPurchase> items) {
        final List<DataDetailsPurchaseItem> itemsDto = new ArrayList<>();
        for (ItemPurchase i : items) {
            itemsDto.add(new DataDetailsPurchaseItem(i));
        }
        return itemsDto;
    }
}
