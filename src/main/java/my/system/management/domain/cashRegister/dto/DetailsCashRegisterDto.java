package my.system.management.domain.cashRegister.dto;

import my.system.management.domain.cashRegister.model.CashRegister;
import my.system.management.domain.enums.Operations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DetailsCashRegisterDto(
        String id,
        LocalDateTime createdAt,
        Operations operation,
        BigDecimal value,
        BigDecimal total
) {
    public DetailsCashRegisterDto(CashRegister cashRegister) {
        this(cashRegister.getId(),
                cashRegister.getCreatedAt(),
                cashRegister.getOperation(),
                cashRegister.getValue(),
                cashRegister.getTotal());
    }
}
