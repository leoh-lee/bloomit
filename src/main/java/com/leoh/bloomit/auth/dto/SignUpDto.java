package com.leoh.bloomit.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpDto {
    private String username;
    private String password;
    private String nickname;
}
