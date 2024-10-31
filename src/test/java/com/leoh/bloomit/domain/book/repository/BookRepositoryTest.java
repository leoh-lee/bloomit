package com.leoh.bloomit.domain.book.repository;

import com.leoh.bloomit.domain.book.entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    void tearDown() {
        bookRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("책 제목으로 LIKE 조회")
    void findByTitleContaining() {
        // given
        String title = "Effective Java";
        String author = "Joshua";

        String title2 = "Java TroubleShooting";
        String author2 = "이상민";

        String title3 = "Toby's Spring";
        String author3 = "Toby";

        createBookAndSave(title, author);
        createBookAndSave(title2, author2);
        createBookAndSave(title3, author3);

        // when
        List<Book> findBooks = bookRepository.findByTitleContaining("Java");
        // then
        assertThat(findBooks).hasSize(2);
        assertThat(findBooks)
                .extracting(Book::getTitle, Book::getAuthor)
                .containsExactly(
                        tuple(title, author),
                        tuple(title2, author2)
                );
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