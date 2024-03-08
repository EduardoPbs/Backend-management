package my.system.management.domain.employee.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import my.system.management.domain.address.dto.DataAddress;

public record DataUpdateEmployee(
        @NotNull
        String id,
        String name,
        @Valid DataAddress adress
) {}
