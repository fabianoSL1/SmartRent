package com.ufrrj.smartrent.utils.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JwtUtils {

    @Value("${api.security.jwt.secret}")
    private String secret;

    public String generateToken(UserDetails user) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("smart-rent-api")
                .withClaim("sub", user.getUsername())
                .withClaim("roles", user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
                )
                .withExpiresAt(generateExpiration())
                .sign(algorithm);
    }

    private Instant generateExpiration() {
        return Instant.now().plusSeconds(3600);
    }

    public DecodedJWT validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT
                .require(algorithm)
                .withIssuer("smart-rent-api")
                .build()
                .verify(token);
    }
}
