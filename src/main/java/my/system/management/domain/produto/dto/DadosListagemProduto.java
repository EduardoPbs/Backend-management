package my.system.management.domain.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import my.system.management.domain.enums.Categoria;
import my.system.management.domain.produto.model.Produto;

import java.math.BigDecimal;

public record DadosListagemProduto(
        String id,
        String nome,
        String codigo,
        BigDecimal valor,
        Categoria categoria,
        Integer quantidade,
        @JsonProperty("ativo")
        Boolean isAtivo) {

    public DadosListagemProduto(Produto produto){
        this(
                produto.getId(),
                produto.getNome(),
                produto.getCodigo(),
                produto.getValor(),
                produto.getCategoria(),
                produto.getQuantidade(),
                produto.isAtivo()
        );
    }
}
