package my.system.management.adapter.in.api;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import my.system.management.domain.employee.model.Employee;
import my.system.management.domain.employee.service.EmployeeService;
import my.system.management.domain.order.dto.DataCreateOrder;
import my.system.management.domain.orderItem.service.OrderItemService;
import my.system.management.domain.order.dto.DataDetailsOrder;
import my.system.management.domain.order.dto.DataFinishOrder;
import my.system.management.domain.order.model.Order;
import my.system.management.domain.order.service.OrderService;
import my.system.management.domain.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity novoPedido(UriComponentsBuilder uriBuilder, @RequestBody DataCreateOrder data) {
        final Order order = orderService.save(data);
        final URI location = uriBuilder.path("pedidos/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(order.getId());
    }

    @PostMapping("/add-items")
    public ResponseEntity addItem(@RequestBody @Valid DataFinishOrder data) {
        orderService.addOrderItems(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<DataDetailsOrder>> getAllOrders(
            @RequestParam(required = false, defaultValue = "date") String sortBy,
            @RequestParam(required = false, defaultValue = "DESC") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        final List<DataDetailsOrder> orders = orderService.findAll(sort);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsOrder> getPedido(@PathVariable("id") String id) {
        final Order order = orderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DataDetailsOrder(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removePedido(@PathVariable("id") String id) {
        final Order orderFounded = orderService.getReferenceById(id);
        orderService.delete(orderFounded);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
