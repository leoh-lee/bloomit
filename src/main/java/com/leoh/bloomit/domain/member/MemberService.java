package com.leoh.bloomit.domain.member;

import com.leoh.bloomit.interfaces.api.member.MemberResponse;
import com.leoh.bloomit.support.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.leoh.bloomit.support.exception.ErrorCode.MEMBER_NOT_FOUND;

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

    public Member getReferenceById(Long id) {
        return memberRepository.getReferenceById(id);
    }
}
