package my.system.management.domain.order.dto;

import my.system.management.domain.orderItem.model.OrderItem;
import my.system.management.domain.order.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DataDetailsOrder(
        String id,
        List<OrderItem> items,
        LocalDateTime date,
        BigDecimal total
) {
    public DataDetailsOrder(Order order){
        this(order.getId(), order.getItems(), order.getDate(), order.getTotal());
    }
}
