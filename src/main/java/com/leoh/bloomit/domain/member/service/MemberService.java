package com.leoh.bloomit.domain.member.service;

import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save(Member member) {
        if (memberRepository.existsByUsername(member.getUsername())) {
            throw new IllegalStateException("Username already exists");
        }

        member.createAndSetLibrary();
        memberRepository.save(member);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username dose not exist"));
    }

}
