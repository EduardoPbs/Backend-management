package my.system.management.domain.funcionario.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import my.system.management.domain.endereco.dto.DadosEndereco;

public record DadosCadastroFuncionario(
        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @Valid
        DadosEndereco endereco
) {}
