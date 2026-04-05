package com.br.demo.domain.repository.user;

import java.util.Optional;

import com.br.demo.domain.model.entity.user.User;
import com.br.demo.domain.model.valueobject.Email;

public interface UserRepository {
    void save(User user);

    Optional<User> findByEmail(Email email);
}
