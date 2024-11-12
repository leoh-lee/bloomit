package com.leoh.bloomit.auth.service;

import com.leoh.bloomit.auth.dto.request.SignInRequest;
import com.leoh.bloomit.auth.dto.request.SignUpRequest;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.service.MemberService;
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

    public JwtToken signIn(SignInRequest signInRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInRequest.username(), signInRequest.password());

        AuthenticationManager authenticationManager = authenticationManagerBuilder.getObject();
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);
    }

    public void signUp(SignUpRequest signUpRequest) {
        String encodedPassword = bCryptPasswordEncoder.encode(signUpRequest.getPassword());
        signUpRequest.changePassword(encodedPassword);
        memberService.save(Member.from(signUpRequest));
    }

}
