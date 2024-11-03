package com.leoh.bloomit.domain.library.service;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.library.dto.response.LibrarySearchResponse;
import com.leoh.bloomit.domain.library.entity.Library;
import com.leoh.bloomit.domain.library.repository.LibraryRepository;
import com.leoh.bloomit.domain.member.entity.Member;
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
        Member member = Member.create("username", "password", "name", "nickname");
        Library library = Library.create(member);

        String bookTitle1 = "effective java";
        String bookTitle2 = "함께자라기";
        String bookTitle3 = "실용주의 프로그래머";

        String author1 = "test";
        String author2 = "test";
        String author3 = "test";

        library.addBooks(List.of(
                Book.builder().title(bookTitle1).author(author1).build(),
                Book.builder().title(bookTitle2).author(author2).build(),
                Book.builder().title(bookTitle3).author(author3).build()
        ));

        when(libraryRepository.findByMemberId(anyLong())).thenReturn(library);

        //when
        LibrarySearchResponse findLibrary = libraryService.findByMemberId(1L);

        //then
        assertThat(findLibrary.getBooks())
                .extracting("title", "author")
                .containsExactlyInAnyOrder(
                        tuple(bookTitle1, author1),
                        tuple(bookTitle2, author2),
                        tuple(bookTitle3, author3)
                );
    }

}