package com.leoh.bloomit.domain.bookauthor.service;

import com.leoh.bloomit.domain.author.dto.response.AuthorResponse;
import com.leoh.bloomit.domain.author.entity.Author;
import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.bookauthor.entity.BookAuthor;
import com.leoh.bloomit.domain.bookauthor.repository.BookAuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookAuthorServiceTest {

    private BookAuthorService bookAuthorService;

    @Mock
    private BookAuthorRepository bookAuthorRepository;

    @BeforeEach
    void setUp() {
        bookAuthorService = new BookAuthorService(bookAuthorRepository);
    }

    @Test
    @DisplayName("책으로 작가 리스트를 조회한다.")
    void searchAuthorsByBook() {
        // given
        Author author = Author.builder()
                .id(1L)
                .name("leoh")
                .birth("2000")
                .imageUrl("url")
                .build();

        when(bookAuthorRepository.findAllByBook(any())).thenReturn(List.of(BookAuthor.builder().book(Book.builder().build()).author(author).build()));
        // when
        List<AuthorResponse> authorResponses = bookAuthorService.searchAuthorsByBook(Book.builder().build());
        // then
        assertThat(authorResponses).hasSize(1).extracting("name").contains("leoh");
    }
}