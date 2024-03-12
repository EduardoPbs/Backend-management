package my.system.management.domain.order.dto;

import my.system.management.domain.employee.dto.DataMinDetailsEmployee;
import my.system.management.domain.employee.model.Employee;
import my.system.management.domain.orderItem.model.OrderItem;
import my.system.management.domain.order.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DataDetailsOrder(
        String id,
        List<OrderItem> items,
        LocalDateTime date,
        DataMinDetailsEmployee employee,
        BigDecimal total
) {
    public DataDetailsOrder(Order order) {
        this(
                order.getId(),
                order.getItems(),
                order.getDate(),
                new DataMinDetailsEmployee(order.getEmployee()),
                order.getTotal()
        );
    }
}
