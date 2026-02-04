package br.com.forumhub.infra.security;

import br.com.forumhub.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration-minutes:120}")
    private long expirationMinutes;

    public String gerarToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        Instant expiresAt = Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES);

        return JWT.create()
                .withIssuer("forumhub")
                .withSubject(usuario.getLogin())
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

    public String validarTokenEObterSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("forumhub")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
