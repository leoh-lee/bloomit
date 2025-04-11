package com.leoh.bloomit.domain.auth;

import com.leoh.bloomit.domain.member.Gender;
import com.leoh.bloomit.domain.member.MemberService;
import com.leoh.bloomit.interfaces.api.auth.request.SignUpRequest;
import com.leoh.bloomit.interfaces.api.member.MemberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional(readOnly = true)
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @DisplayName("회원 가입 성공. 비밀번호는 암호화되어 저장")
    @Test
    @Transactional
    void signUpSuccess() {
        // given
        String username = "username";
        String name = "name";
        String password = "password";
        String nickname = "nickname";
        Gender gender = Gender.MALE;

        SignUpRequest signUpRequest = new SignUpRequest(username, password, name, nickname, gender);

        // when
        authService.signUp(signUpRequest);
        // then
        MemberResponse findMember = memberService.findByUsername(username);
        assertThat(findMember).isNotNull()
                .extracting("username", "nickname", "name")
                .containsExactly(username, nickname, name);
    }

}