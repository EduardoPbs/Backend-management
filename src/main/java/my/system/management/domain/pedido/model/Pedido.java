package my.system.management.domain.pedido.model;

import jakarta.persistence.*;
import lombok.*;
import my.system.management.domain.itensPedido.model.ItemPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "pedidos")
@Entity(name = "Pedido")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Pedido {

    @Id
    private String id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @Setter
    private LocalDateTime data;

    @Setter
    private BigDecimal total;

    public Pedido(String id, List<ItemPedido> itens, BigDecimal total){
        this.id = id;
        this.itens = itens;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", itens=" + itens +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
