package my.system.management.domain.orderItem.repository;

import my.system.management.domain.orderItem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

}
