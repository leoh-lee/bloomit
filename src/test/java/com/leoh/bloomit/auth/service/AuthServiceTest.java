package com.leoh.bloomit.auth.service;

import com.leoh.bloomit.auth.dto.SignUpDto;
import com.leoh.bloomit.member.entity.Member;
import com.leoh.bloomit.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @DisplayName("회원 가입 성공. 비밀번호는 암호화되어 저장")
    @Test
    void signUpSuccess() {
        // given
        String username = "username";
        String password = "password";
        String nickname = "nickname";
        SignUpDto signUpDto = new SignUpDto(username, password, nickname);
        // when
        authService.signUp(signUpDto);
        // then
        Member findMember = memberService.findByUsername(username);
        assertThat(findMember).isNotNull()
                .extracting("username", "nickname")
                .containsExactly(username, nickname);

        assertThat(bCryptPasswordEncoder.matches(password, findMember.getPassword())).isTrue();
    }

}