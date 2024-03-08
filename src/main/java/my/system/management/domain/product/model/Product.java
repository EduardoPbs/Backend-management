package my.system.management.domain.product.model;

import jakarta.persistence.*;
import lombok.*;
import my.system.management.domain.enums.Category;
import my.system.management.domain.product.dto.DataUpdateProduct;
import my.system.management.domain.product.dto.DataCreateProduct;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Table(name = "products")
@Entity(name = "product")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Product {

    @Id
    private String id;
    private String name;
    private String code;
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Setter
    private Integer stock;
    private boolean active;

    public Product() {
        this.id = UUID.randomUUID().toString();
    }

    public Product(DataCreateProduct data) {
        this.id = UUID.randomUUID().toString();
        this.name = data.name();
        this.code = data.code();
        this.value = data.value();
        this.category = data.category();
        this.stock = data.stock();
        this.active = true;
    }

    public void delete() {
        this.active = false;
    }

    public void update(DataUpdateProduct data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.value() != null) {
            this.value = data.value();
        }

        if (data.category() != null) {
            this.category = data.category();
        }

        if (data.stock() != null) {
            this.stock = data.stock();
        }

        if (data.active() != this.active) {
            this.active = data.active();
        }
    }
}
