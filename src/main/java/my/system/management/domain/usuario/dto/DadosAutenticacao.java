package my.system.management.domain.usuario.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutenticacao(
        @JsonAlias({"email"}) // Apelido que tambem é aceito no parametro como >>login<< (pode ter mais que um)
        String login,
        @JsonAlias({"senha"})
        String password) {
}
