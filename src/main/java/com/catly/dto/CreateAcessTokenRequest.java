package com.catly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAcessTokenRequest {
    private String refreshToken;
}
