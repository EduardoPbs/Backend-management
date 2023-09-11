package my.system.management.domain.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import my.system.management.domain.enums.Categoria;
import my.system.management.domain.produto.model.Produto;

import java.math.BigDecimal;

public record DadosDetalhesProduto(
        String id,
        String nome,
        String codigo,
        BigDecimal valor,
        Integer quantidade,
        Categoria categoria,
        @JsonProperty("ativo")
        Boolean isAtivo) {
    public DadosDetalhesProduto(Produto produto){
        this(
                produto.getId(),
                produto.getNome(),
                produto.getCodigo(),
                produto.getValor(),
                produto.getQuantidade(),
                produto.getCategoria(),
                produto.isAtivo()
        );
    }
}
