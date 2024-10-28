package com.leoh.bloomit.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignInDto {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;
}
