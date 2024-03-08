package my.system.management.domain.cashRegister.dto;

import java.math.BigDecimal;

public record DetailsCashRegisterDto(
    BigDecimal total
) {
}
