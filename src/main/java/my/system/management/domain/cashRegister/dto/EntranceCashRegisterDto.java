package my.system.management.domain.cashRegister.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record EntranceCashRegisterDto(
        @NotBlank
        BigDecimal value
) {
}
