package com.leoh.bloomit.domain.book.service;

import com.leoh.bloomit.common.exception.ErrorCode;
import com.leoh.bloomit.common.exception.ResourceNotFoundException;
import com.leoh.bloomit.domain.author.dto.response.AuthorResponse;
import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.respository.BookRepository;
import com.leoh.bloomit.domain.bookauthor.service.BookAuthorService;
import com.leoh.bloomit.domain.rating.service.RatingService;
import com.leoh.bloomit.domain.review.dto.response.ReviewResponse;
import com.leoh.bloomit.domain.review.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookAuthorService bookAuthorService;

    @Mock
    private RatingService ratingService;

    @Mock
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository, bookAuthorService, ratingService, reviewService);
    }

    @Test
    @DisplayName("책 상세 조회 시 책이 없으면 ResourceNotFoundException 발생")
    void searchBookDetail_not_found() {
        // given
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> bookService.searchBookDetail(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(ErrorCode.BOOK_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("책 상세 조회")
    void searchBookDetail() {
        // given
        Book book = Book.builder()
                .id(1L)
                .title("Effective Java")
                .pages(100)
                .content("목차")
                .description("섦명")
                .imageUrl("url")
                .subTitle("부제목")
                .publishedDate("2024-11-17")
                .build();

        BigDecimal ratingAvg = BigDecimal.valueOf(2.5);
        int ratingCount = 1;

        when(bookRepository.findById(any())).thenReturn(Optional.of(book));
        when(bookAuthorService.searchAuthorsByBook(any())).thenReturn(List.of(AuthorResponse.builder().build()));
        when(ratingService.getRatingAvgByBook(any())).thenReturn(ratingAvg);
        when(ratingService.countByBook(any())).thenReturn(ratingCount);
        when(reviewService.searchReviewsByBook(any())).thenReturn(List.of(ReviewResponse.builder().build()));
        // when
        // then
        assertThat(bookService.searchBookDetail(1L))
                .extracting("bookId", "title", "ratingAvg", "ratingCount")
                .containsExactly(book.getId(), book.getTitle(), ratingAvg, ratingCount);
    }

}