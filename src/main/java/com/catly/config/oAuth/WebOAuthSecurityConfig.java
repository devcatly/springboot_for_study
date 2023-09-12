package com.catly.config.oAuth;

import com.catly.config.jwt.Tokenprovider;
import com.catly.repository.RefreshTokenRepository;
import com.catly.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {
    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final Tokenprovider tokenprovider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(toH2Conole())
                .requestMatchers("/img/**", "/css/**","/js/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws  Exception{
        http.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable();
    http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    http.authorizeHttpRequests()
            .requestMatchers("/api/token").permitAll()
            .requestMatchers("/api/**").authenticated()
            .anyRequest().permitAll();

    http.oauth2Login()
            .loginPage("/login")
            .authorizationEndpoint()
            .authorizationRequestRepository(oAuth2Auth)
    }

    @Bean
    public OAuth2S

}
