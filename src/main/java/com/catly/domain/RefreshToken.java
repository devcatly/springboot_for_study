package com.catly.domain;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Transactional
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long Id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    public RefreshToken(long userId, String refreshToken){
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String newRefreshToken){
        this.refreshToken = newRefreshToken;
        return this;
    }
}
