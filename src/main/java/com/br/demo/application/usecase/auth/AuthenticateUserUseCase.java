package com.br.demo.application.usecase.auth;

import org.springframework.stereotype.Service;

import com.br.demo.application.dto.input.login.LoginInput;
import com.br.demo.application.dto.output.login.LoginOutput;
import com.br.demo.domain.model.valueobject.Email;
import com.br.demo.domain.repository.user.UserRepository;
import com.br.demo.domain.service.PasswordHashService;
import com.br.demo.domain.service.TokenService;

@Service
public class AuthenticateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordHashService passwordHashService;
    private final TokenService tokenService;

    public AuthenticateUserUseCase(
            UserRepository userRepository,
            PasswordHashService passwordHashService,
            TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordHashService = passwordHashService;
        this.tokenService = tokenService;
    }

    public LoginOutput execute(LoginInput input) {
        Email email = new Email(input.email());
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordHashService.matches(input.password(), user.getPassword().getValue())) {
            throw new RuntimeException("Senha inválida");
        }

        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);

        return new LoginOutput(accessToken, refreshToken);
    }

}
