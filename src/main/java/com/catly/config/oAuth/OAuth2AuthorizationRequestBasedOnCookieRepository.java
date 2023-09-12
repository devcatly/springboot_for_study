package com.catly.config.oAuth;


import com.catly.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.web.util.WebUtils;

public class OAuth2AuthorizationRequestBasedOnCookieRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public final static String OAUT2_AUTHORIZATION_REQUEST_COOKIE_NAME ="oauth2_auth_request";
    private final static int COOKIE_EXPIRE_SECONDS = 10000;

    @Override
    public OAuth2AuthorizationRequest removeAUAuthorizationRequest(HttpServletRequest request, HttpServletResponse response){
        return this.loadAuthorizationRequest(request);
    }

    @Override
    public OAuth2AuthorizationRequest lOAuth2AuthorizationRequest(HttpServletRequest request){
        Cookie cookie = WebUtils.getCookie(request, OAUT2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        return CoO
    }
}
