package my.system.management.domain.order.service;

import jakarta.persistence.EntityNotFoundException;
import my.system.management.domain.cashRegister.model.CashRegister;
import my.system.management.domain.cashRegister.service.CashRegisterService;
import my.system.management.domain.employee.model.Employee;
import my.system.management.domain.employee.repository.EmployeeRepository;
import my.system.management.domain.employee.service.EmployeeService;
import my.system.management.domain.order.dto.DataCreateOrder;
import my.system.management.domain.order.dto.DataFinishOrder;
import my.system.management.domain.order.model.Order;
import my.system.management.domain.order.repository.OrderRepository;
import my.system.management.domain.orderItem.dto.DataCreateOrderItem;
import my.system.management.domain.orderItem.model.OrderItem;
import my.system.management.domain.orderItem.repository.OrderItemRepository;
import my.system.management.domain.product.model.Product;
import my.system.management.domain.product.repository.ProductRepository;
import my.system.management.infra.exception.PedidoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CashRegisterService cashRegisterService;

    public List<Order> findAll(Sort sort) {
        return orderRepository.findAll(sort);
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    public Order getReferenceById(String id) {
        return orderRepository.getReferenceById(id);
    }

    public Order save(DataCreateOrder order) {
        final Employee employeeFound = employeeRepository
                .findById(order.employeeId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));

        final Order newOrder = new Order(
                new ArrayList<>(),
                employeeFound,
                BigDecimal.ZERO
        );
        newOrder.setDate(LocalDateTime.now());

        return orderRepository.save(newOrder);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public void addOrderItems(DataFinishOrder data) {
        Order orderRecuperado = orderRepository.findById(data.orderId()).orElseThrow(() -> new PedidoNotFoundException());
        List<OrderItem> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (DataCreateOrderItem item : data.dataItems()) {
            Product productRecuperado = productRepository
                    .findById(item.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

            if (item.quantity() > productRecuperado.getStock()) {
                throw new IllegalArgumentException("Estoque insuficiente. " +
                        "Produto: " + productRecuperado.getName() +
                        " Estoque: " + productRecuperado.getStock());
            }

            productRecuperado.setStock(
                    productRecuperado.getStock() - item.quantity());

            OrderItem novoItem = new OrderItem(
                    item.quantity(),
                    orderRecuperado,
                    productRecuperado
            );

            BigDecimal valorItem = BigDecimal.valueOf(item.quantity()).multiply(productRecuperado.getValue());
            total = total.add(valorItem);
            itens.add(novoItem);
        }

        orderRecuperado.setTotal(total);

        cashRegisterService.doEntrance(orderRecuperado.getTotal());
        orderItemRepository.saveAll(itens);
    }
}
