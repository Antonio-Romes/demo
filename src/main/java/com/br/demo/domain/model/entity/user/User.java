package com.br.demo.domain.model.entity.user;

import java.time.LocalDateTime;

import com.br.demo.domain.model.enums.Role;
import com.br.demo.domain.model.valueobject.Email;
import com.br.demo.domain.model.valueobject.Password;

public class User {
    private Long id;
    private String nomeCompleto;
    private String nickname;
    private Email email;
    private Password password;
    private Role role;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private User(
            Long id,
            String nomeCompleto,
            String nickname,
            Email email,
            Password password,
            Role role,
            boolean enabled,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User create(
            String nomeCompleto,
            String nickname,
            Email email,
            Password password,
            Role role) {
        return new User(
                null,
                nomeCompleto,
                nickname,
                email,
                password,
                Role.User,
                true,
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public static User restore(
            Long id,
            String nomeCompleto,
            String nickname,
            Email email,
            Password password,
            Role role,
            boolean enabled,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new User(
                id,
                nomeCompleto,
                nickname,
                email,
                password,
                role,
                enabled,
                createdAt,
                updatedAt);
    }

    public void disable() {
        this.enabled = false;
        this.updatedAt = LocalDateTime.now();
    }

    public void enable() {
        this.enabled = true;
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
