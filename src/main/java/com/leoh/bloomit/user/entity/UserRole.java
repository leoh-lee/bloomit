package com.leoh.bloomit.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ADMIN("관리자"),
    USER("일반 유저"),
    GUEST("게스트"),
    UNKNOWN("알 수 없음")
    ;

    private final String description;

    public static UserRole findByName(String roleNameStr) {
        if (!StringUtils.hasText(roleNameStr)) {
            return UNKNOWN;
        }
        return Arrays.stream(values())
                .filter(role -> role.name().equals(roleNameStr.toUpperCase()))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
