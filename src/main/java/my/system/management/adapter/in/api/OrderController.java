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

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    @Transactional
    public ResponseEntity novoPedido(UriComponentsBuilder uriBuilder, @RequestBody DataCreateOrder data) {
        final Order order = orderService.save(data);
        final URI location = uriBuilder.path("pedidos/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(order.getId());
    }

    @PostMapping("/add-items")
    @Transactional
    public ResponseEntity addItem(@RequestBody @Valid DataFinishOrder data) {
        orderService.addOrderItems(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<DataDetailsOrder>> getAllOrders() {
        final List<Order> orders = orderService.findAll();
        List<DataDetailsOrder> dadosPedidos = new ArrayList<>();

        for (Order p : orders) {
            dadosPedidos.add(new DataDetailsOrder(p));
        }

        return ResponseEntity.status(HttpStatus.OK).body(dadosPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getPedido(@PathVariable("id") String id) {
        final Order order = orderService.getReferenceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removePedido(@PathVariable("id") String id) {
        final Order orderRecuperado = orderService.getReferenceById(id);
        orderService.delete(orderRecuperado);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
