package my.system.management.adapter.in.api;

import jakarta.persistence.EntityNotFoundException;
import my.system.management.domain.orderItem.model.OrderItem;
import my.system.management.domain.orderItem.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("order-item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> get(@PathVariable("id") String id) {
        final OrderItem itemList = orderItemService
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado."));
        return ResponseEntity.status(HttpStatus.OK).body(itemList);
    }
}
