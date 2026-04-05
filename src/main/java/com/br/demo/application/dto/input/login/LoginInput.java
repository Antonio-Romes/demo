package com.br.demo.application.dto.input.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginInput(
                @Email(message = "Email no formato inválido") @NotBlank(message = "Email é obrigatório") String email,

                @NotBlank(message = "Senha é obrigatória") @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres") String password) {

}
