package com.leoh.bloomit.domain.auth;

import com.leoh.bloomit.domain.member.Member;
import com.leoh.bloomit.domain.member.MemberService;
import com.leoh.bloomit.interfaces.api.auth.request.SignInRequest;
import com.leoh.bloomit.interfaces.api.auth.request.SignUpRequest;
import com.leoh.bloomit.support.security.JwtToken;
import com.leoh.bloomit.support.security.JwtTokenProvider;
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

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword());

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
