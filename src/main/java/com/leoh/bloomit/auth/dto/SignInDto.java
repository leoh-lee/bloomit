package com.leoh.bloomit.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record SignInDto(@NotBlank String username, @NotBlank String password) {}
