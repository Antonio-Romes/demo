package com.br.demo.domain.model.valueobject;

public class Password {
    private final String value;

    public Password(String value) {
        if (value == null || value.length() < 8) {
            throw new IllegalArgumentException("Usuário ou Senha inválida");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
