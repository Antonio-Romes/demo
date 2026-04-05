package com.br.demo.infrastructure.persistence.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.demo.infrastructure.persistence.entity.user.UserEntity;
import java.util.Optional;

public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
