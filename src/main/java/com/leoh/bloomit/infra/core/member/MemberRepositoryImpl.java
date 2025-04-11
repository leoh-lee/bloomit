package com.leoh.bloomit.infra.core.member;

import com.leoh.bloomit.domain.member.Member;
import com.leoh.bloomit.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> findByUsername(String username) {
        return memberJpaRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return memberJpaRepository.existsByUsername(username);
    }

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public Member getReferenceById(Long id) {
        return memberJpaRepository.getReferenceById(id);
    }
}
