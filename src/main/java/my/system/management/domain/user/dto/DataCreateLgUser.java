package my.system.management.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataCreateLgUser(
        @NotBlank(message = "Email obrigatotio")
        @Email
        String login,

        @NotBlank(message = "Senha obrigatoria")
        @Pattern(regexp = ".{6,}")
        String password
) {
}
