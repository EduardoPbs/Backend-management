package my.system.management.domain.itensPedido.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosCadastroItemPedido(
        Integer quantidade,

        @JsonAlias("produto_id")
        String produtoId

) {
}
