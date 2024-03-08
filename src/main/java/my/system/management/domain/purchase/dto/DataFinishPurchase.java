package my.system.management.domain.purchase.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import my.system.management.domain.itemPurchase.dto.DataCreateItemPurchase;

import java.util.List;

public record DataFinishPurchase(
        @NotBlank
        String purchase_id,

        @JsonAlias("data_items")
        List<DataCreateItemPurchase> dataItems
) {
}
