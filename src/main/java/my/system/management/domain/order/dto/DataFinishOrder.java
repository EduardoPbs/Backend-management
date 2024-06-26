package my.system.management.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import my.system.management.domain.orderItem.dto.DataCreateOrderItem;

import java.util.List;

public record DataFinishOrder(
        @NotBlank
        @JsonAlias("order_id")
        String orderId,

        @JsonAlias("data_items")
        List<DataCreateOrderItem> dataItems
) {
}
