package com.br.demo.infrastructure.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.demo.domain.service.PasswordHashService;

@Service
public class BCryptPasswordHashService implements PasswordHashService {

    private final PasswordEncoder passwordEncoder;

    public BCryptPasswordHashService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String hash(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

}
