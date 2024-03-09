package my.system.management.domain.orderItem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import my.system.management.domain.order.model.Order;
import my.system.management.domain.product.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "order_items")
@Entity(name = "Order_item")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @Setter
    private String id;

    @Column(name = "valor_unidade")
    private BigDecimal unityValue;

    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    private Order order;

    @ManyToOne
    private Product product;

    public OrderItem(Integer quantity, Order order, Product product) {
        this.id = UUID.randomUUID().toString();
        this.unityValue = product.getValue();
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    @Override
    public String toString() {
        return ("  _________Item Pedido_________  " + System.getProperty("line.separator") +
                " | ID: " + this.getId() + System.getProperty("line.separator") +
                " | Valor Unidade: " + this.getUnityValue() + System.getProperty("line.separator") +
                " | Quantidade: " + this.getQuantity() + System.getProperty("line.separator") +
                " | Pedido ID: " + this.getOrder().getId() + System.getProperty("line.separator") +
                " | Produto ID: " + this.getProduct().getId() + System.getProperty("line.separator")
        );
    }
}
