package com.br.demo.domain.service;

import com.br.demo.domain.model.entity.user.User;

public interface TokenService {
    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    String validateToken(String token);
}
