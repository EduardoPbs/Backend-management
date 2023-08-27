package my.system.management.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuario(
        @NotBlank(message = "Email obrigatotio")
        @Email
        String login,

        @NotBlank(message = "Senha obrigatoria")
        @Pattern(regexp = ".{6,}")
        String password
) {
}
