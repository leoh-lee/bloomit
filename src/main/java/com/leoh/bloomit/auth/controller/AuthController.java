package com.leoh.bloomit.auth.controller;

import com.leoh.bloomit.auth.dto.request.SignUpRequest;
import com.leoh.bloomit.auth.service.AuthService;
import com.leoh.bloomit.auth.dto.request.SignInRequest;
import com.leoh.bloomit.security.jwt.JwtToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtToken> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        JwtToken jwtToken = authService.signIn(signInRequest);
        if (ObjectUtils.isEmpty(jwtToken)) {
            throw new UsernameNotFoundException("User not found");
        }
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());

        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
    }

}
