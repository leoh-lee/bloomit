package com.leoh.bloomit.domain.member.service;

import com.leoh.bloomit.domain.library.dto.response.LibrarySearchResponse;
import com.leoh.bloomit.domain.library.service.LibraryService;
import com.leoh.bloomit.domain.member.dto.response.MemberResponse;
import com.leoh.bloomit.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private LibraryService libraryService;

    @DisplayName("save 호출 시 username이 동일한 Member가 존재하면 IllegalStateException이 발생한다.")
    @Test
    void saveUsernameDuplicatedException() {
        // given
        String username = "username";
        String nickname = "nickname";
        String name = "name";
        String password = "password";
        Member member = createMember(username, nickname, name, password);
        Member member2 = createMember(username, "nickname2", "name2", "password2");
        memberService.save(member);
        // when
        // then
        assertThatThrownBy(() -> memberService.save(member2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Username already exists");
    }

    @DisplayName("회원 등록 성공하고, 서재가 해당 회원의 하나 생성된다.")
    @Test
    void saveSuccess() {
        // given
        String username = "username";
        String nickname = "nickname";
        String name = "name";
        String password = "password";
        Member member = createMember(username, nickname, name, password);

        // when
        memberService.save(member);
        Member findMember = memberService.findByUsername(username);
        LibrarySearchResponse librarySearchResponse = libraryService.findByMemberId(findMember.getId());
        // then
        assertThat(findMember).extracting("username", "nickname", "name")
                .containsExactly(username, nickname, name);

        assertThat(librarySearchResponse).isNotNull()
                        .extracting(LibrarySearchResponse::getMember)
                        .returns("username", MemberResponse::getUsername)
                        .returns("nickname", MemberResponse::getNickname)
                        .returns("name", MemberResponse::getName);

    }

    private Member createMember(String username, String nickname, String name, String password) {
        return Member.builder()
                .username(username)
                .nickname(nickname)
                .name(name)
                .password(password)
                .build();
    }
}