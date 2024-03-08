package my.system.management.domain.itemPurchase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.system.management.domain.order.model.Order;
import my.system.management.domain.product.model.Product;
import my.system.management.domain.purchase.model.Purchase;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "items_purchase")
@Entity(name = "item_purchase")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class ItemPurchase {

    @Id
    private String id;

    @Column(name = "valor_unidade")
    private BigDecimal unityValue;

    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    private Purchase purchase;

    @ManyToOne
    @JsonIgnore
    private Product product;

    public ItemPurchase(Integer quantity, Purchase purchase, Product product) {
        this.id = UUID.randomUUID().toString();
        this.unityValue = product.getValue();
        this.quantity = quantity;
        this.purchase = purchase;
        this.product = product;
    }

    @Override
    public String toString() {
        return ("  _________Item Compra_________  " + System.getProperty("line.separator") +
                " | ID: " + this.getId() + System.getProperty("line.separator") +
                " | Valor Unidade: " + this.getUnityValue() + System.getProperty("line.separator") +
                " | Quantidade: " + this.getQuantity() + System.getProperty("line.separator") +
                " | Pedido ID: " + this.getPurchase().getId() + System.getProperty("line.separator") +
                " | Produto ID: " + this.getProduct().getId() + System.getProperty("line.separator")
        );
    }
}
