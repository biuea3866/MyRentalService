package com.microservices.authservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length=50, unique = true)
    private String email;

    @Column(nullable = false, length = 50, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false, unique = true)
    private String encryptedPwd;

    @Builder
    public UserEntity(
        String email,
        String nickname,
        String phoneNumber,
        String userId,
        String createdAt,
        String encryptedPwd
    ) {
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.createdAt = createdAt;
        this.encryptedPwd = encryptedPwd;
    }
}
