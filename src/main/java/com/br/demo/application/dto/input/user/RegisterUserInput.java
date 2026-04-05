package com.br.demo.application.dto.input.user;

public record RegisterUserInput(
        String nomeCompleto,
        String nickname,
        String email,
        String password) {
}
