package com.leoh.bloomit.support.security;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtToken {

    private final String grantType;     // ex) Bearer
    private final String accessToken;
    private final String refreshToken;

}
