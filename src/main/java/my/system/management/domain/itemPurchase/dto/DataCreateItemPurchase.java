package my.system.management.domain.itemPurchase.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataCreateItemPurchase(
        Integer quantity,

        @JsonAlias("produto_id")
        String productId
) {

}
