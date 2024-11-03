package com.leoh.bloomit.domain.member.service;

import com.leoh.bloomit.common.exception.ErrorCode;
import com.leoh.bloomit.common.exception.ResourceNotFoundException;
import com.leoh.bloomit.domain.library.dto.response.LibrarySearchResponse;
import com.leoh.bloomit.domain.library.service.LibraryService;
import com.leoh.bloomit.domain.member.dto.response.MemberResponse;
import com.leoh.bloomit.domain.member.entity.Member;
import org.junit.jupiter.api.BeforeEach;
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

    private Member member;
    private static final String USERNAME = "username";
    private static final String NICKNAME = "nickname";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";

    @BeforeEach
    void setUp() {
        member = Member.create(USERNAME, PASSWORD, NAME, NICKNAME);
    }

    @DisplayName("save 호출 시 username이 동일한 Member가 존재하면 IllegalStateException이 발생한다.")
    @Test
    void saveUsernameDuplicatedException() {
        // given
        Member member2 = Member.create(USERNAME, "password2", "name2", "nickname2");
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
        // when
        memberService.save(member);
        MemberResponse findMember = memberService.findByUsername(USERNAME);
        LibrarySearchResponse librarySearchResponse = libraryService.findByMemberId(findMember.getId());
        // then
        assertThat(findMember).extracting("username", "nickname", "name")
                .containsExactly(USERNAME, NICKNAME, NAME);

        assertThat(librarySearchResponse).isNotNull()
                        .extracting(LibrarySearchResponse::getMember)
                        .returns(USERNAME, MemberResponse::getUsername)
                        .returns(NICKNAME, MemberResponse::getNickname)
                        .returns(NAME, MemberResponse::getName);

    }

    @Test
    @DisplayName("유저명으로 회원 조회 시, 해당 회원이 없으면 ResourceNotFoundException 발생")
    void findByUsernameNotFoundException() {
        // given
        // when
        // then
        assertThatThrownBy(() -> memberService.findByUsername(USERNAME))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(ErrorCode.MEMBER_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("유저명으로 회원 조회")
    void findByUsername() {
        // given
        memberService.save(member);
        // when
        MemberResponse findMember = memberService.findByUsername(USERNAME);
        // then
        assertThat(findMember)
                .extracting("username", "nickname", "name")
                .containsExactly(USERNAME, NICKNAME, NAME);
    }

}
