package my.system.management.domain.cashRegister.model;

import jakarta.persistence.*;
import lombok.*;
import my.system.management.domain.enums.Operations;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "cash_register")
@Entity(name = "CashRegister")
@NoArgsConstructor
public class CashRegister {

    @Id
    private String id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Operations operation;

    private BigDecimal value = BigDecimal.ZERO;

    @Getter
    private BigDecimal total = BigDecimal.ZERO;

    public CashRegister(BigDecimal value) {
        this.id = UUID.randomUUID().toString();
        this.total = value;
    }

    public void entrance(BigDecimal value) {
        this.operation = Operations.ENTRANCE;
        this.value = value;
        this.total = this.total.add(value);
    }

    public void pullout(BigDecimal value) {
        this.operation = Operations.PULLOUT;
        this.value = value;
        this.total = this.total.subtract(value);
    }

    @Override
    public String toString() {
        return "{ Id: " + this.id +
                " Created At: " + this.createdAt +
                " Op: " + this.operation +
                " Total: " + this.total + " }";
    }

    public String getId() {
        return this.id;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Operations getOperation() {
        return this.operation;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public BigDecimal getTotal() {
        return this.total;
    }
}
