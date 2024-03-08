package my.system.management.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataAuth(
        @JsonAlias({"email"}) // Apelido que tambem é aceito no parametro como >>login<< (pode ter mais que um)
        String login,
        @JsonAlias({"senha"})
        String password
) {
}
