package com.br.demo.presentation.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.demo.application.dto.input.login.LoginInput;
import com.br.demo.application.dto.input.user.RegisterUserInput;
import com.br.demo.application.dto.output.login.LoginOutput;
import com.br.demo.application.dto.output.user.UserOutput;
import com.br.demo.application.usecase.auth.AuthenticateUserUseCase;
import com.br.demo.application.usecase.user.RegisterUserUseCase;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase, AuthenticateUserUseCase authenticateUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserOutput> Register(@RequestBody @Valid RegisterUserInput input) {
        return ResponseEntity.ok(registerUserUseCase.execute(input));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginOutput> login(@RequestBody @Valid LoginInput input) {
        return ResponseEntity.ok(authenticateUserUseCase.execute(input));
    }

    @GetMapping("/test")
    public String test() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.toString();
    }

}
