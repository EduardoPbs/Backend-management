package my.system.management.domain.produto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.system.management.domain.enums.Categoria;
import my.system.management.domain.produto.dto.DadosAtualizadosProduto;
import my.system.management.domain.produto.dto.DadosCadastroProduto;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    private String id;
    private String nome;
    private String codigo;
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Integer quantidade;
    private boolean ativo;

    public Produto(DadosCadastroProduto dados){
        this.id = UUID.randomUUID().toString();
        this.nome = dados.nome();
        this.codigo = dados.codigo();
        this.valor = dados.valor();
        this.categoria = dados.categoria();
        this.quantidade = dados.quantidade();
        this.ativo = true;
    }

    public void excluir(){
        this.ativo = false;
    }

    public void atualizar(DadosAtualizadosProduto dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }

        if(!dados.valor().equals(null)){
            this.valor.add(dados.valor());
        }

        if(dados.categoria() != null){
            this.categoria = dados.categoria();
        }

        if(dados.quantidade() != null){
            this.quantidade = dados.quantidade();
        }
    }
}
