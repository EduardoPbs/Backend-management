package my.system.management.domain.funcionario.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import my.system.management.domain.endereco.dto.DadosEndereco;

public record DadosAtualizadosFuncionario(
        @NotNull
        String id,
        String nome,
        @Valid DadosEndereco endereco
) {}
