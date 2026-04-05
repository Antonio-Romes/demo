package com.br.demo.infrastructure.persistence.repository.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.br.demo.domain.model.entity.user.User;
import com.br.demo.domain.model.valueobject.Email;
import com.br.demo.domain.repository.user.UserRepository;
import com.br.demo.infrastructure.persistence.entity.user.UserEntity;
import com.br.demo.infrastructure.persistence.mapper.user.UserMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJPA repository;

    public UserRepositoryImpl(UserRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        UserEntity savedEntity = repository.save(UserMapper.toEntity(user));
        user.setId(savedEntity.getId());
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return repository.findByEmail(email.getValue())
                .map(UserMapper::toUser);
    }

}
