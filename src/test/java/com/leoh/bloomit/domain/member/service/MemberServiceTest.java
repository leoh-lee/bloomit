package com.leoh.bloomit.domain.member.service;

import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
    }

    @DisplayName("save 호출 시 username이 동일한 Member가 존재하면 IllegalStateException이 발생한다.")
    @Test
    void saveUsernameDuplicatedException() {
        // given
        String username = "username";
        Member member = Member.builder()
                .username(username)
                .build();
        memberRepository.save(member);
        // when
        // then
        assertThatThrownBy(() -> memberService.save(member))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Username already exists");
    }

    @DisplayName("save 호출 시 username이 동일한 Member가 존재하지 않으면 정상적으로 저장된다.")
    @Test
    void saveSuccess() {
        // given
        String username = "username";
        String nickname = "nickname";
        Member member = Member.builder()
                .username(username)
                .nickname(nickname)
                .build();
        // when
        memberService.save(member);
        // then
        Member findMember = memberService.findByUsername(username);
        assertThat(findMember).extracting("username", "nickname")
                .containsExactly(username, nickname);
    }
}