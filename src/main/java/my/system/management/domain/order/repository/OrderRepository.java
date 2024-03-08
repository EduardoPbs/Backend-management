package my.system.management.domain.order.repository;

import my.system.management.domain.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
