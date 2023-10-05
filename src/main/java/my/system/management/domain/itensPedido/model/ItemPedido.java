package my.system.management.domain.itensPedido.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import my.system.management.domain.pedido.model.Pedido;
import my.system.management.domain.produto.model.Produto;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "itens_pedido")
@Entity(name = "ItensPedido")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id
    @Setter
    private String id;

    @Column(name = "valor_unidade")
    private BigDecimal valorUnidade;

    private Integer quantidade;

    @ManyToOne
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JsonIgnore
    private Produto produto;

    public ItemPedido(Integer quantidade, Pedido pedido, Produto produto) {
        this.id = UUID.randomUUID().toString();
        this.valorUnidade = produto.getValor();
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return ("  _________Item Pedido_________  " + System.getProperty("line.separator") +
                " | ID: " + this.getId() + System.getProperty("line.separator") +
                " | Valor Unidade: " + this.getValorUnidade() + System.getProperty("line.separator") +
                " | Quantidade: " + this.getQuantidade() + System.getProperty("line.separator") +
                " | Pedido ID: " + this.getPedido().getId() + System.getProperty("line.separator") +
                " | Produto ID: " + this.getProduto().getId() + System.getProperty("line.separator")
        );
    }
}
