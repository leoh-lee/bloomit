package com.leoh.bloomit.interfaces.api.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignInRequest {

    @NotBlank
    private final String username;

    @NotBlank
    private final String password;

}
