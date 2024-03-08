package my.system.management.domain.cashRegister.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record PulloutCashRegisterDto(
        @NotBlank
        BigDecimal value
) {
}
