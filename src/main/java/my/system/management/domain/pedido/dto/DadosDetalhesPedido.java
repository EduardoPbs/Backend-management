package my.system.management.domain.pedido.dto;

import my.system.management.domain.itensPedido.model.ItemPedido;
import my.system.management.domain.pedido.model.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhesPedido(
        String id,
        List<ItemPedido> itens,
        LocalDateTime data,
        BigDecimal total
) {
    public DadosDetalhesPedido(Pedido pedido){
        this(pedido.getId(), pedido.getItens(), pedido.getData(), pedido.getTotal());
    }
}
