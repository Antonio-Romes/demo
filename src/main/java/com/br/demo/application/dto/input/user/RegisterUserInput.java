package com.br.demo.application.dto.input.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserInput(
                @NotBlank(message = "Nome completo é obrigatório") String nomeCompleto,
                @NotBlank(message = "Apelido é obrigatório") String nickname,
                @NotBlank(message = "Email é obrigatório") @Email(message = "Email no formato inválido") String email,
                @NotBlank(message = "Senha é obrigatória") @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres") String password) {
}
