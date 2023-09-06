package com.catly.config.jwt;

import com.catly.domain.User;
import com.catly.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenproviderTest {
    @Autowired
    private Tokenprovider tokenprovider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtPropertise jwtPropertise;

    @DisplayName("generateToken(): 유저정보와 만료기간을 전달해 토큰")
    @Test
    void generateToken(){
        //given
        User testUser = userRepository.save(User.builder()
                .email("user@email.com")
                .password("test")
                .build());
        //when
       String token = tokenprovider.generateToken(testUser, Duration.ofDays(14));
       //then
        Long userId = Jwts.parser()
                .setSigningKey(jwtPropertise.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id",Long.class);
        assertThat(userId).isEqualTo(testUser.getId());
    }

    @DisplayName("validToken(): 토큰만료로 실패")
    @Test
    void vaiidToken_invalidToekn() {
        //given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtPropertise);
        //when
        boolean result = tokenprovider.validToken(token);
        //then
        assertThat(result).isFalse();
    }

    @DisplayName("vaildToken():토큰성공")
    @Test
    void vaildToken_validToken()
    {
        //given
        String token = JwtFactory.withDefaultValues()
                .createToken(jwtPropertise);
        //when
        boolean result = tokenprovider.validToken(token);
        //then
        assertThat(result).isTrue();
    }

    @DisplayName("Authrionticaion")
    @Test
    void getAuthentication(){
        //given
        String userEmail = "user@gmail.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtPropertise);
        //when
    Authentication authentication = tokenprovider.getAuthentication(token);
        //then
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
    }

    @DisplayName("getUserId()")
    @Test
    void getUserId(){
        //given
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id",userId))
                .build()
                .createToken(jwtPropertise);
        //when
        Long userIdByToken = tokenprovider.getUserId(token);

        //then
        assertThat(userIdByToken).isEqualTo(userId);
    }


}