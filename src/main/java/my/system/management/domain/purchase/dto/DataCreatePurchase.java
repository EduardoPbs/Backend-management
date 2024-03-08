package my.system.management.domain.purchase.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import my.system.management.domain.itemPurchase.model.ItemPurchase;

import java.math.BigDecimal;
import java.util.List;

public record DataCreatePurchase(
        @JsonAlias("items")
        List<ItemPurchase> items,

        @NotBlank
        BigDecimal total
) {
}
