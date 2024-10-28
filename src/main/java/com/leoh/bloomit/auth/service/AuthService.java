package com.leoh.bloomit.auth.service;

import com.leoh.bloomit.auth.dto.SignInDto;
import com.leoh.bloomit.auth.dto.SignUpDto;
import com.leoh.bloomit.member.entity.Member;
import com.leoh.bloomit.member.service.MemberService;
import com.leoh.bloomit.security.jwt.JwtToken;
import com.leoh.bloomit.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JwtToken signIn(SignInDto signInDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword());

        AuthenticationManager authenticationManager = authenticationManagerBuilder.getObject();
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);
    }

    public void signUp(SignUpDto signUpDto) {
        String encodedPassword = bCryptPasswordEncoder.encode(signUpDto.getPassword());
        signUpDto.changePassword(encodedPassword);
        memberService.save(Member.from(signUpDto));
    }

}
