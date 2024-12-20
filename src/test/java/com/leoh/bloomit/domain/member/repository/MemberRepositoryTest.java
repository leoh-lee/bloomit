package com.leoh.bloomit.domain.member.repository;

import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.enums.Gender;
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
        String name = "name";
        String password = "password";
        Gender gender = Gender.MALE;

        Member member = Member.create(username, password, name, nickname, gender);

        memberRepository.save(member);

        // when
        Optional<Member> findMember = memberRepository.findByUsername(username);
        // then
        assertThat(findMember).isPresent()
                .get()
                .extracting("username", "nickname", "name")
                .containsExactly(username, nickname, name);
    }

    @DisplayName("existsByUsername 호출 시 username이 동일한 Member가 이미 존재하면 true 반환")
    @Test
    void existsByUsernameTrue() {
        // given
        String username = "username";
        String nickname = "dog";
        String name = "name";
        String password = "password";
        Gender gender = Gender.MALE;

        Member member = Member.create(username, password, name, nickname, gender);
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