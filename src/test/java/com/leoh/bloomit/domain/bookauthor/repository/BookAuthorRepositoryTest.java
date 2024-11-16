package com.leoh.bloomit.domain.bookauthor.repository;

import com.leoh.bloomit.domain.author.entity.Author;
import com.leoh.bloomit.domain.author.repository.AuthorRepository;
import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.respository.BookRepository;
import com.leoh.bloomit.domain.bookauthor.entity.BookAuthor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BookAuthorRepositoryTest {

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        bookAuthorRepository.deleteAllInBatch();
        authorRepository.deleteAllInBatch();
        bookRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("책으로 작가 리스트 조회")
    void findAuthorsByBook() {
        // given
        Book book = saveDummyBook();
        Author author1 = Author.builder()
                .name("leoh")
                .birth("2000")
                .imageUrl("url")
                .build();

        Author author2 = Author.builder()
                .name("linux")
                .birth("2000")
                .imageUrl("url")
                .build();

        authorRepository.save(author1);
        authorRepository.save(author2);

        BookAuthor bookAuthor1 = BookAuthor.builder().author(author1).book(book).build();
        BookAuthor bookAuthor2 = BookAuthor.builder().author(author2).book(book).build();
        bookAuthorRepository.save(bookAuthor1);
        bookAuthorRepository.save(bookAuthor2);

        // when
        List<BookAuthor> bookAuthors = bookAuthorRepository.findAllByBook(book);
        // then
        assertThat(bookAuthors).hasSize(2).extracting(bookAuthor -> bookAuthor.getAuthor().getName()).contains("leoh", "linux");
    }

    private Book saveDummyBook() {
        Book book = Book.builder()
                .title("test")
                .isbn("test")
                .pages(100)
                .bookAuthors(List.of(BookAuthor.builder().build()))
                .build();
        bookRepository.save(book);
        return book;
    }

}
