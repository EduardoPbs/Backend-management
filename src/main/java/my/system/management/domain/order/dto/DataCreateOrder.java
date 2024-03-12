package my.system.management.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataCreateOrder(
        @JsonAlias("employee_id")
        String employeeId
) {
}
