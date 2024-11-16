package com.leoh.bloomit.domain.member.service;

import com.leoh.bloomit.common.exception.ResourceNotFoundException;
import com.leoh.bloomit.domain.member.dto.response.MemberResponse;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.leoh.bloomit.common.exception.ErrorCode.MEMBER_NOT_FOUND;

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

        memberRepository.save(member);
    }

    public MemberResponse findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .map(MemberResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException(MEMBER_NOT_FOUND));
    }

}
