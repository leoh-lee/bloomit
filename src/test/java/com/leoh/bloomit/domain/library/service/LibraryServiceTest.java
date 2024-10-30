package com.leoh.bloomit.domain.library.service;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.repository.BookRepository;
import com.leoh.bloomit.domain.library.dto.response.LibrarySearchResponse;
import com.leoh.bloomit.domain.library.entity.Library;
import com.leoh.bloomit.domain.library.repository.LibraryRepository;
import com.leoh.bloomit.domain.librarybook.entity.LibraryBook;
import com.leoh.bloomit.domain.librarybook.repository.LibraryBookRepository;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class LibraryServiceTest {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryBookRepository libraryBookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    void tearDown() {
        libraryBookRepository.deleteAllInBatch();
        libraryRepository.deleteAllInBatch();
        bookRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("특정 회원의 서재를 조회한다.")
    void findByMember() {
        //given
        Member member = createMember("username", "nickname", "name", "password");
        memberRepository.save(member);

        Library library = Library.create(member);
        libraryRepository.save(library);

        String bookTitle1 = "effective java";
        String bookTitle2 = "함께자라기";
        String bookTitle3 = "실용주의 프로그래머";

        String author1 = "test";
        String author2 = "test";
        String author3 = "test";

        Book effectiveJava = createBook(bookTitle1, author1);
        Book growthTogether = createBook(bookTitle2, author2);
        Book programmer = createBook(bookTitle3, author3);

        bookRepository.saveAll(List.of(
                effectiveJava,
                growthTogether,
                programmer
        ));

        LibraryBook libraryBook1 = LibraryBook.create(library, effectiveJava);
        LibraryBook libraryBook2 = LibraryBook.create(library, growthTogether);
        LibraryBook libraryBook3 = LibraryBook.create(library, programmer);

        libraryBookRepository.saveAll(List.of(
                libraryBook1,
                libraryBook2,
                libraryBook3
        ));

        //when
        LibrarySearchResponse findLibrary = libraryService.findByMemberId(member.getId());

        //then
        assertThat(findLibrary.getBooks())
                .extracting("title", "author")
                .containsExactlyInAnyOrder(
                        tuple(bookTitle1, author1),
                        tuple(bookTitle2, author2),
                        tuple(bookTitle3, author3)
                );
    }

    private Book createBook(String bookTitle, String author) {
        return Book.builder()
                .isbn(UUID.randomUUID().toString())
                .title(bookTitle)
                .author(author)
                .publisher("인사이트")
                .publicationDate(LocalDateTime.now())
                .build();
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