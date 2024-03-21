package my.system.management.domain.purchase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import my.system.management.domain.enums.PurchaseStatus;
import my.system.management.domain.itemPurchase.model.ItemPurchase;
import my.system.management.domain.purchase.dto.DataCreatePurchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "purchases")
@Entity(name = "Purchase")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Purchase {

    @Id
    private String id;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<ItemPurchase> items = new ArrayList<>();

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    private BigDecimal total;

    public Purchase(List<ItemPurchase> items, BigDecimal total){
        this.id = UUID.randomUUID().toString();
        this.items = items;
        this.date = LocalDateTime.now();
        this.status = PurchaseStatus.PENDENTE;
        this.total = total;
    }

    public Purchase(DataCreatePurchase p) {
        this.id = UUID.randomUUID().toString();
        this.items = p.items();
        this.total = p.total();
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

    public void completeStatus() {
        this.status = PurchaseStatus.FINALIZADO;
    }

    public void pendingStatus() {
        this.status = PurchaseStatus.PENDENTE;
    }
}
