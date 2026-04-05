package com.br.demo.infrastructure.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.demo.domain.model.entity.user.User;
import com.br.demo.domain.service.TokenService;

@Service
public class JwtTokenService implements TokenService {

    private final String SECRET = "minha-chave-secreta";

    @Override
    public String generateAccessToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail().getValue())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) // 15 min
                .sign(Algorithm.HMAC256(SECRET));
    }

    @Override
    public String generateRefreshToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail().getValue())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 dia
                .sign(Algorithm.HMAC256(SECRET));
    }

    @Override
    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
