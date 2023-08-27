package my.system.management.domain.funcionario.model;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.system.management.domain.endereco.model.Endereco;
import my.system.management.domain.funcionario.dto.DadosAtualizadosFuncionario;
import my.system.management.domain.funcionario.dto.DadosCadastroFuncionario;

import java.util.UUID;

@Table(name = "funcionario")
@Entity(name = "Funcionario")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {

    @Id
    private String id;
    private String nome;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Funcionario(DadosCadastroFuncionario dados){
        this.id = UUID.randomUUID().toString();
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizar(DadosAtualizadosFuncionario dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.endereco() != null){
            this.endereco.atualizarEndereco(dados.endereco());
        }
    }
}
