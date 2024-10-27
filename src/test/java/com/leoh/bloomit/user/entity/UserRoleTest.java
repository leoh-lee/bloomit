package com.leoh.bloomit.user.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class UserRoleTest {

    @DisplayName("Role name에 해당하는 Role을 찾을 수 있다.")
    @MethodSource("roleNameAndUserRole")
    @ParameterizedTest
    void findByName(String roleNameStr, UserRole userRole) {
        // given
        UserRole findRole = UserRole.findByName(roleNameStr);
        // when // then
        assertThat(findRole).isSameAs(userRole);
    }

    @DisplayName("찾으려는 Role name이 null이면 UserRole.UNKNOWN 이 반환된다.")
    @Test
    void argIsNullUnknown() {
        // given
        UserRole findRole = UserRole.findByName(null);
        // when // then
        assertThat(findRole).isSameAs(UserRole.UNKNOWN);
    }

    @DisplayName("찾으려는 Role name이 빈 문자열이면 UserRole.UNKNOWN 이 반환된다.")
    @Test
    void argIsEmptyUnknown() {
        // given
        UserRole findRole = UserRole.findByName("");
        // when // then
        assertThat(findRole).isSameAs(UserRole.UNKNOWN);
    }

    @DisplayName("찾으려는 Role name이 UserRole에 없으면 UserRole.UNKNOWN 이 반환된다.")
    @Test
    void argIsUnknown() {
        // given
        UserRole findRole = UserRole.findByName("TEST");
        // when // then
        assertThat(findRole).isSameAs(UserRole.UNKNOWN);
    }

    private static Stream<Arguments> roleNameAndUserRole() {
        return Stream.of(
                Arguments.of("admin", UserRole.ADMIN),
                Arguments.of("ADMIN", UserRole.ADMIN),
                Arguments.of("user", UserRole.USER),
                Arguments.of("USER", UserRole.USER),
                Arguments.of("guest", UserRole.GUEST),
                Arguments.of("GUEST", UserRole.GUEST)
        );
    }

}