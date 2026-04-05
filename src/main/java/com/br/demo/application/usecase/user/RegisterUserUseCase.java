package com.br.demo.application.usecase.user;

import org.springframework.stereotype.Service;

import com.br.demo.application.dto.input.user.RegisterUserInput;
import com.br.demo.application.dto.output.user.UserOutput;
import com.br.demo.domain.model.entity.user.User;
import com.br.demo.domain.model.enums.Role;
import com.br.demo.domain.model.valueobject.Email;
import com.br.demo.domain.model.valueobject.Password;
import com.br.demo.domain.repository.user.UserRepository;
import com.br.demo.domain.service.PasswordHashService;

@Service
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordHashService passwordHashService;

    public RegisterUserUseCase(
            UserRepository userRepository,
            PasswordHashService passwordHashService) {
        this.userRepository = userRepository;
        this.passwordHashService = passwordHashService;
    }

    public UserOutput execute(RegisterUserInput input) {
        Email email = new Email(input.email());

        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new RuntimeException("Usuário já existe");
                });

        String hashedPassword = passwordHashService.hash(input.password());
        Password password = new Password(hashedPassword);

        User user = User.create(
                input.nomeCompleto(),
                input.nickname(),
                email,
                password,
                Role.User);

        userRepository.save(user);

        return new UserOutput(user.getId(), user.getEmail().getValue());
    }
}
