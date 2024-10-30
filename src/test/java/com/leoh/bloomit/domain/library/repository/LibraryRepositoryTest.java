package com.leoh.bloomit.domain.library.repository;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.repository.BookRepository;
import com.leoh.bloomit.domain.library.entity.Library;
import com.leoh.bloomit.domain.librarybook.repository.LibraryBookRepository;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class LibraryRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryBookRepository libraryBookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("특정 회원의 서재를 조회한다2.")
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

        Book effectiveJava = createBookAndSave(bookTitle1, author1);
        Book growthTogether = createBookAndSave(bookTitle2, author2);
        Book programmer = createBookAndSave(bookTitle3, author3);

        library.addBooks(List.of(
                effectiveJava, growthTogether, programmer
        ));

        libraryBookRepository.saveAll(library.getLibraryBooks());

        em.flush();
        em.clear();
        // when
        Library findLibrary = libraryRepository.findByMemberId(member.getId());

        // then
        assertThat(findLibrary.getId()).isEqualTo(library.getId());
        assertThat(findLibrary.getMember().getId()).isEqualTo(member.getId());
        assertThat(findLibrary.getBooks())
                .extracting("title", "author")
                .containsExactlyInAnyOrder(
                        tuple(bookTitle1, author1),
                        tuple(bookTitle2, author2),
                        tuple(bookTitle3, author3)
                );
    }

    private Book createBookAndSave(String bookTitle, String author) {
        Book book = Book.builder()
                .isbn(UUID.randomUUID().toString())
                .title(bookTitle)
                .author(author)
                .publisher("인사이트")
                .publicationDate(LocalDateTime.now())
                .build();

        bookRepository.save(book);

        return book;
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