package com.catly.controller;

import com.catly.dto.CreateAcessTokenRequest;
import com.catly.dto.CreateAcessTokenResponse;
import com.catly.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAcessTokenResponse> createNewAccessToken(@RequestBody CreateAcessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAcessTokenResponse(newAccessToken));
    }
}