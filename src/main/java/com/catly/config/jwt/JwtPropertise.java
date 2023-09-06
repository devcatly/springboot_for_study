package com.catly.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt")
public class JwtPropertise {
    private String issuer;
    private String secretKey;
}
