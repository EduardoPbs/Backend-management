package my.system.management.domain.employee.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import my.system.management.domain.address.dto.DataAddress;

public record DataCreateEmployee(
        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @Valid
        DataAddress address
) {}
