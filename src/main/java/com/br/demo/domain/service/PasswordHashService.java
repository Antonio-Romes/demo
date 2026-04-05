package com.br.demo.domain.service;

public interface PasswordHashService {
    String hash(String password);

    boolean matches(String rawPassword, String hashedPassword);
}
