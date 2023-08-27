package my.system.management.domain.funcionario.dto;

import my.system.management.domain.endereco.model.Endereco;
import my.system.management.domain.funcionario.model.Funcionario;

public record DadosDetalhesFuncionario(String id, String nome, String cpf, Endereco endereco) {
    public DadosDetalhesFuncionario(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getEndereco());
    }
}
