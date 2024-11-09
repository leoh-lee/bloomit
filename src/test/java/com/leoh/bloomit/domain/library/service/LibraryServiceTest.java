package com.leoh.bloomit.domain.library.service;

import com.leoh.bloomit.domain.library.dto.response.LibrarySearchResponse;
import com.leoh.bloomit.domain.library.entity.Library;
import com.leoh.bloomit.domain.library.repository.LibraryRepository;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class LibraryServiceTest {

    @Autowired
    private LibraryService libraryService;

    @MockBean
    private LibraryRepository libraryRepository;

    @Test
    @DisplayName("특정 회원의 서재를 조회한다.")
    void findByMember() {
        //given
        Member member = Member.create("username", "password", "name", "nickname", Gender.MALE);
        String libraryName = "library1";
        Library library = Library.create(member, libraryName);

        when(libraryRepository.findByMemberId(anyLong())).thenReturn(List.of(library));

        //when
        List<LibrarySearchResponse> findLibrary = libraryService.findByMemberId(1L);

        //then
        assertThat(findLibrary)
                .extracting("libraryName")
                .containsExactlyInAnyOrder(
                        libraryName
                );
    }

}