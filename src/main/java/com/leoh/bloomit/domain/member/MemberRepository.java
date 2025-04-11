package com.leoh.bloomit.domain.member;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository {
    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);

    void save(Member member);

    Member getReferenceById(Long id);
}
