package com.catly.config.oAuth;

import com.catly.config.jwt.Tokenprovider;
import com.catly.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class OAu2SucessHandler extends SimpleUrlAuthenticationFailureHandler {
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration FRASH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);
    public static final String REDIRECT_PATH = "/articles";

    private final Tokenprovider tokenprovider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final Oauth2A
}
