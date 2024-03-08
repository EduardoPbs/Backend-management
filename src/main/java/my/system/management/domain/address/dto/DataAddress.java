package my.system.management.domain.address.dto;

import jakarta.validation.constraints.NotBlank;

public record DataAddress(

        @NotBlank
        String street,

        @NotBlank
        String district,

        String number,
        String complement
) {}
