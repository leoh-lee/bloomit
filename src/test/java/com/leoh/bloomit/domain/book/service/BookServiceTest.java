package com.leoh.bloomit.domain.book.service;

import com.leoh.bloomit.domain.book.dto.request.BookSaveRequest;
import com.leoh.bloomit.domain.book.dto.request.BookSearchRequest;
import com.leoh.bloomit.domain.book.dto.response.BookResponse;
import com.leoh.bloomit.domain.book.enums.BookSearchType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    @DisplayName("BookSearchRequest의 bookSearchType이 null이면 IllegalArgumentException 발생")
    void validateBookSearchTypeOfBookSearchRequest() {
        // given
        BookSearchRequest bookSearchRequest = BookSearchRequest.builder()
                .bookSearchType(null)
                .keyword("keyword")
                .build();
        // when
        // then
        assertThatThrownBy(() -> bookService.search(bookSearchRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("BookSearchType cannot be null or empty");
    }

    @Test
    @DisplayName("BookSearchRequest의 keyword가 null이거나 공백이면 IllegalArgumentException 발생")
    void validateKeywordOfBookSearchRequest() {
        // given
        BookSearchRequest keywordNull = BookSearchRequest.builder()
                .bookSearchType(BookSearchType.TITLE)
                .keyword(null)
                .build();
        BookSearchRequest keywordEmpty = BookSearchRequest.builder()
                .bookSearchType(BookSearchType.TITLE)
                .keyword("")
                .build();
        BookSearchRequest keywordBlank = BookSearchRequest.builder()
                .bookSearchType(BookSearchType.TITLE)
                .keyword(" ")
                .build();
        // when
        // then
        assertThatThrownBy(() -> bookService.search(keywordNull))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Keyword cannot be null or empty");

        assertThatThrownBy(() -> bookService.search(keywordEmpty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Keyword cannot be null or empty");

        assertThatThrownBy(() -> bookService.search(keywordBlank))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Keyword cannot be null or empty");
    }

    @Test
    @DisplayName("책 조회 시 조회 결과가 없으면 빈 리스트 반환를 반환한다.")
    void searchResultIsEmpty() {
        // given
        BookSearchRequest request = BookSearchRequest.builder()
                .bookSearchType(BookSearchType.TITLE)
                .keyword("keyword")
                .build();
        // when
        List<BookResponse> result = bookService.search(request);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("책 이름으로 책 목록을 조회한다.(LIKE)")
    void searchBookByTitle() {
        // given
        BookSaveRequest request1 = BookSaveRequest.builder()
                .title("Effective Java")
                .author("Joshua")
                .isbn(UUID.randomUUID().toString())
                .publisher("인사이트")
                .publishedDate(LocalDateTime.now())
                .build();

        BookSaveRequest request2 = BookSaveRequest.builder()
                .title("Java TroubleShooting")
                .author("이상민")
                .isbn(UUID.randomUUID().toString())
                .publisher("제이펍")
                .publishedDate(LocalDateTime.now())
                .build();

        BookSaveRequest request3 = BookSaveRequest.builder()
                .title("Toby's Spring")
                .author("Toby")
                .isbn(UUID.randomUUID().toString())
                .publisher("에이콘")
                .publishedDate(LocalDateTime.now())
                .build();

        bookService.save(request1);
        bookService.save(request2);
        bookService.save(request3);

        BookSearchRequest bookSearchRequest = BookSearchRequest.builder()
                .bookSearchType(BookSearchType.TITLE)
                .keyword("Java")
                .build();
        // when
        List<BookResponse> bookResponses = bookService.search(bookSearchRequest);

        // then
        assertThat(bookResponses).hasSize(2);
        assertThat(bookResponses)
                .extracting(BookResponse::getTitle, BookResponse::getAuthor)
                .containsExactlyInAnyOrder(
                        tuple("Effective Java", "Joshua"),
                        tuple("Java TroubleShooting", "이상민")
                );
    }

    @Test
    @DisplayName("작가 명으로 책 목록을 조회한다.(LIKE)")
    void searchBookByAuthor() {
        // given
        String author = "Joshua lee";
        String author2 = "Kim Joshua";
        String wrongAuthor = "author";

        BookSaveRequest request = BookSaveRequest.builder()
                .title("Effective Java")
                .author(author)
                .isbn(UUID.randomUUID().toString())
                .publisher("인사이트")
                .publishedDate(LocalDateTime.now())
                .build();

        BookSaveRequest request2 = BookSaveRequest.builder()
                .title("Effective Java")
                .author(author2)
                .isbn(UUID.randomUUID().toString())
                .publisher("인사이트")
                .publishedDate(LocalDateTime.now())
                .build();

        BookSaveRequest request3 = BookSaveRequest.builder()
                .title("Effective Java")
                .author(wrongAuthor)
                .isbn(UUID.randomUUID().toString())
                .publisher("인사이트")
                .publishedDate(LocalDateTime.now())
                .build();

        bookService.save(request);
        bookService.save(request2);
        bookService.save(request3);

        BookSearchRequest bookSearchRequest = BookSearchRequest.builder()
                .bookSearchType(BookSearchType.AUTHOR)
                .keyword("Joshua")
                .build();

        // when
        List<BookResponse> bookResponses = bookService.search(bookSearchRequest);

        // then
        assertThat(bookResponses).hasSize(2);
        assertThat(bookResponses)
                .extracting(BookResponse::getAuthor)
                .containsExactlyInAnyOrder("Joshua lee", "Kim Joshua");
    }

    @Test
    @DisplayName("ISBN으로 책 하나를 조회한다.")
    void searchBookByIsbn() {
        // given
        String isbn = UUID.randomUUID().toString();

        BookSaveRequest request = BookSaveRequest.builder()
                .title("Effective Java")
                .author("Joshua")
                .isbn(isbn)
                .publisher("인사이트")
                .publishedDate(LocalDateTime.now())
                .build();

        bookService.save(request);

        BookSearchRequest bookSearchRequest = BookSearchRequest.builder()
                .bookSearchType(BookSearchType.ISBN)
                .keyword(isbn)
                .build();

        // when
        List<BookResponse> bookResponses = bookService.search(bookSearchRequest);

        // then
        assertThat(bookResponses).hasSize(1);
        assertThat(bookResponses)
                .extracting(BookResponse::getTitle, BookResponse::getAuthor)
                .containsExactlyInAnyOrder(
                        tuple("Effective Java", "Joshua")
                );
    }

    @Test
    @DisplayName("출판사 명으로 책 목록을 조회한다.")
    void searchBookByPublisher() {
        // given
        String targetPublisher = "인사이트";

        BookSaveRequest request = BookSaveRequest.builder()
                .title("request1")
                .author("Joshua")
                .isbn(UUID.randomUUID().toString())
                .publisher("인사이트 아웃")
                .publishedDate(LocalDateTime.now())
                .build();

        BookSaveRequest request2 = BookSaveRequest.builder()
                .title("request2")
                .author("Joshua")
                .isbn(UUID.randomUUID().toString())
                .publisher("wrongPublisher")
                .publishedDate(LocalDateTime.now())
                .build();

        BookSaveRequest request3 = BookSaveRequest.builder()
                .title("request3")
                .author("Joshua")
                .isbn(UUID.randomUUID().toString())
                .publisher("인사이트 인")
                .publishedDate(LocalDateTime.now())
                .build();

        bookService.save(request);
        bookService.save(request2);
        bookService.save(request3);

        BookSearchRequest bookSearchRequest = BookSearchRequest.builder()
                .bookSearchType(BookSearchType.PUBLISHER)
                .keyword(targetPublisher)
                .build();

        // when
        List<BookResponse> bookResponses = bookService.search(bookSearchRequest);

        // then
        assertThat(bookResponses).hasSize(2);
        assertThat(bookResponses)
                .extracting(BookResponse::getTitle, BookResponse::getPublisher)
                .containsExactlyInAnyOrder(
                        tuple("request1", "인사이트 아웃"),
                        tuple("request3", "인사이트 인")
                );
    }

}