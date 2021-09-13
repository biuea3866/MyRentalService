package com.microservices.authservice.repository;

import com.microservices.authservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);
}
