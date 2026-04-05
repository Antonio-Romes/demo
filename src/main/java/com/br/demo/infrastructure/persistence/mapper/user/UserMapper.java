package com.br.demo.infrastructure.persistence.mapper.user;

import com.br.demo.domain.model.entity.user.User;
import com.br.demo.domain.model.valueobject.Email;
import com.br.demo.domain.model.valueobject.Password;
import com.br.demo.infrastructure.persistence.entity.user.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setNomeCompleto(user.getNomeCompleto());
        entity.setNickname(user.getNickname());
        entity.setEmail(user.getEmail().getValue());
        entity.setPassword(user.getPassword().getValue());
        entity.setRole(user.getRole());
        entity.setEnabled(user.isEnabled());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        return entity;
    }

    public static User toUser(UserEntity entity) {
        User user = User.restore(
                entity.getId(),
                entity.getNomeCompleto(),
                entity.getNickname(),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                entity.getRole(),
                entity.isEnabled(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
        return user;
    }

}
