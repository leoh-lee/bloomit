package com.leoh.bloomit.member.repository;

import com.leoh.bloomit.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("username(사용자 ID)에 해당하는 Member가 없으면 Optional.empty()를 반환한다.")
    @Test
    void userNotExistsByUsername() {
        // given
        // when
        Optional<Member> none = memberRepository.findByUsername("none");
        // then
        assertThat(none).isEmpty();
    }

    @DisplayName("username(사용자 ID)에 해당하는 Member가 있으면 Optional에 감싸 Member를 반환한다.")
    @Test
    void userExistsByUsername() {
        // given
        String username = "test";
        String nickname = "dog";
        Member member = Member.builder()
                .username(username)
                .nickname(nickname)
                .build();
        memberRepository.save(member);

        // when
        Optional<Member> findMember = memberRepository.findByUsername(username);
        // then
        assertThat(findMember).isPresent()
                .get()
                .extracting("username", "nickname")
                .containsExactly(username, nickname);
    }
}