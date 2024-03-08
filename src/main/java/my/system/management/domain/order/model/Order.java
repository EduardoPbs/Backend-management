package my.system.management.domain.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import my.system.management.domain.employee.model.Employee;
import my.system.management.domain.orderItem.model.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "orders")
@Entity(name = "Order")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Order {

    @Id
    private String id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    private LocalDateTime date;

    private BigDecimal total;

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    public Order(List<OrderItem> items, BigDecimal total){
        this.id = UUID.randomUUID().toString();
        this.items = items;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", items=" + items +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
