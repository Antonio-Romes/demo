package com.br.demo.application.dto.output.login;

public record LoginOutput(
        String accessToken,
        String refreshToken) {

}
