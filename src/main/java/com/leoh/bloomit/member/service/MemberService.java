package com.leoh.bloomit.member.service;

import com.leoh.bloomit.member.entity.Member;
import com.leoh.bloomit.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(Member member) {
        if (memberRepository.existsByUsername(member.getUsername())) {
            throw new IllegalStateException("Username already exists");
        }
        memberRepository.save(member);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
    }
}
