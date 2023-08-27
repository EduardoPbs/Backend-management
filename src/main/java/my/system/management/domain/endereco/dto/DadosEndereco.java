package my.system.management.domain.endereco.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(

        @NotBlank
        String rua,

        @NotBlank
        String bairro,

        String numero,
        String complemento
) {}
