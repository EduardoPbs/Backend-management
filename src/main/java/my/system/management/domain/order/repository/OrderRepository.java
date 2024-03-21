package my.system.management.domain.order.repository;

import my.system.management.domain.order.model.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAll(Sort sort);
}
