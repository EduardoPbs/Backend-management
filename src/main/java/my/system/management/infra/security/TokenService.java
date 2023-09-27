package my.system.management.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import my.system.management.domain.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final long TOKEN_VALIDITY = Duration.ofMinutes(10).toMillis();

    public String gerarToken(Usuario usuario) {
        final Instant now = Instant.now();
        final Instant expires = now.plusMillis(TOKEN_VALIDITY);
        try {
            var algoritmo = Algorithm.HMAC512(secret);
            return JWT.create()
                    .withIssuer("Management App")
                    .withSubject(usuario.getUsername())
                    .withClaim("roles", usuario.getRoles())
                    .withExpiresAt(expires)
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC512(secret);
            return JWT.require(algoritmo)
                    .withIssuer("Management App")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
}