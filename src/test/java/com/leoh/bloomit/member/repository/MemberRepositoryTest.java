package com.leoh.bloomit.member.repository;

import com.leoh.bloomit.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TestEntityManager em;

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

    @DisplayName("existsByUsername 호출 시 username이 동일한 Member가 이미 존재하면 true 반환")
    @Test
    void existsByUsernameTrue() {
        // given
        String username = "username";
        Member member = Member.builder().username(username).build();
        memberRepository.save(member);
        em.flush();
        // when
        boolean result = memberRepository.existsByUsername(username);
        // then
        assertThat(result).isTrue();
    }

    @DisplayName("existsByUsername 호출 시 username이 동일한 Member가 없으면 false 반환")
    @Test
    void existsByUsernameFalse() {
        // given
        String username = "username";
        // when
        boolean result = memberRepository.existsByUsername(username);
        // then
        assertThat(result).isFalse();
    }
}