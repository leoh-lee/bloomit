package com.leoh.bloomit.domain.library.repository;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.repository.BookRepository;
import com.leoh.bloomit.domain.library.entity.Library;
import com.leoh.bloomit.domain.librarybook.repository.LibraryBookRepository;
import com.leoh.bloomit.domain.member.entity.Member;
import com.leoh.bloomit.domain.member.enums.Gender;
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
    @DisplayName("특정 회원의 서재를 조회한다.")
    void findByMember() {
        //given
        Member member = Member.create("username", "password", "name", "nickname", Gender.MALE);
        memberRepository.save(member);

        String bookTitle1 = "effective java";
        String bookTitle2 = "함께자라기";
        String bookTitle3 = "실용주의 프로그래머";
        String author1 = "test";
        String author2 = "test";
        String author3 = "test";

        Book effectiveJava = createBookAndSave(bookTitle1, author1);
        Book growthTogether = createBookAndSave(bookTitle2, author2);
        Book programmer = createBookAndSave(bookTitle3, author3);

        Library library1 = Library.create(member, "library1");
        Library library2 = Library.create(member, "library2");

        library1.addBooks(List.of(effectiveJava, growthTogether, programmer));
        library2.addBooks(List.of(effectiveJava, programmer));

        libraryRepository.saveAll(List.of(library1, library2));
        em.flush();
        em.clear();
        // when
        List<Library> findLibraries = libraryRepository.findByMemberId(member.getId());
        // then
        assertThat(findLibraries).hasSize(2);
        assertThat(findLibraries.get(0).getLibraryBooks()).hasSize(3);
        assertThat(findLibraries.get(1).getLibraryBooks()).hasSize(2);
        assertThat(findLibraries.get(0).getMember().getId()).isEqualTo(member.getId());
        assertThat(findLibraries.get(1).getMember().getId()).isEqualTo(member.getId());
    }

    private Book createBookAndSave(String bookTitle, String author) {
        Book book = Book.builder()
                .isbn(UUID.randomUUID().toString())
                .title(bookTitle)
                .author(author)
                .publisher("인사이트")
                .publishedDate(LocalDateTime.now())
                .build();

        bookRepository.save(book);

        return book;
    }

}