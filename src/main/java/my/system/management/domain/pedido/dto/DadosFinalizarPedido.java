package my.system.management.domain.pedido.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import my.system.management.domain.itensPedido.dto.DadosCadastroItemPedido;

import java.util.List;

public record DadosFinalizarPedido(
        @NotBlank
        String pedido_id,
        @JsonAlias("data_items")
        List<DadosCadastroItemPedido> dataItems
) {
}
