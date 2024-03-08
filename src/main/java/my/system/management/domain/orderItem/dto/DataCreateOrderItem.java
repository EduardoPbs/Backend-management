package my.system.management.domain.orderItem.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataCreateOrderItem(
        Integer quantity,

        @JsonAlias("produto_id")
        String productId

) {
}
