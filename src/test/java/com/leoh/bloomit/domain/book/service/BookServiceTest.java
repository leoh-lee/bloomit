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
        BookSearchRequest bookSearchRequest = createBookSearchRequest(null, "keyword");
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
        BookSearchRequest keywordNull = createBookSearchRequest(BookSearchType.TITLE, null);
        BookSearchRequest keywordEmpty = createBookSearchRequest(BookSearchType.TITLE, "");
        BookSearchRequest keywordBlank = createBookSearchRequest(BookSearchType.TITLE, " ");
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
        BookSearchRequest request = createBookSearchRequest(BookSearchType.TITLE, "keyword");
        // when
        List<BookResponse> result = bookService.search(request);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("책 이름으로 책 목록을 조회한다.(LIKE)")
    void searchBookByTitle() {
        // given
        createAndSaveRequest("Effective Java", "Joshua", UUID.randomUUID().toString(), "인사이트");
        createAndSaveRequest("Java TroubleShooting", "이상민", UUID.randomUUID().toString(), "제이펍");
        createAndSaveRequest("Toby's Spring", "Toby", UUID.randomUUID().toString(), "에이콘");

        BookSearchRequest bookSearchRequest = createBookSearchRequest(BookSearchType.TITLE, "Java");

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
        String targetAuthor1 = "Joshua lee";
        String targetAuthor2 = "Kim Joshua";
        String wrongAuthor = "author";

        createAndSaveRequest("Effective Java", targetAuthor1, "인사이트");
        createAndSaveRequest("Effective Java", targetAuthor2, "인사이트");
        createAndSaveRequest("Effective Java", wrongAuthor, "인사이트");

        BookSearchRequest bookSearchRequest =  createBookSearchRequest(BookSearchType.AUTHOR, "Joshua");

        // when
        List<BookResponse> bookResponses = bookService.search(bookSearchRequest);

        // then
        assertThat(bookResponses).hasSize(2);
        assertThat(bookResponses)
                .extracting(BookResponse::getAuthor)
                .containsExactlyInAnyOrder(targetAuthor1, targetAuthor2);
    }

    @Test
    @DisplayName("ISBN으로 책 하나를 조회한다.")
    void searchBookByIsbn() {
        // given
        String isbn = UUID.randomUUID().toString();

        createAndSaveRequest("Effective Java", "Joshua", isbn, "인사이트");

        BookSearchRequest bookSearchRequest = createBookSearchRequest(BookSearchType.ISBN, isbn);

        // when
        List<BookResponse> bookResponses = bookService.search(bookSearchRequest);

        // then
        assertThat(bookResponses).hasSize(1);
        assertThat(bookResponses)
                .extracting(BookResponse::getTitle, BookResponse::getAuthor, BookResponse::getIsbn)
                .containsExactlyInAnyOrder(
                        tuple("Effective Java", "Joshua", isbn)
                );
    }

    @Test
    @DisplayName("출판사 명으로 책 목록을 조회한다.")
    void searchBookByPublisher() {
        // given
        String targetPublisher = "인사이트";

        createAndSaveRequest("request1", "Joshua", "인사이트 아웃");
        createAndSaveRequest("request2", "Joshua", "wrongPublisher");
        createAndSaveRequest("request3", "Joshua", "인사이트 인");

        BookSearchRequest bookSearchRequest = createBookSearchRequest(BookSearchType.PUBLISHER, targetPublisher);

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

    private BookSearchRequest createBookSearchRequest(BookSearchType bookSearchType, String keyword) {
        return BookSearchRequest.builder()
                .bookSearchType(bookSearchType)
                .keyword(keyword)
                .build();
    }

    private void createAndSaveRequest(String title, String author, String isbn, String publisher) {
        BookSaveRequest request = BookSaveRequest.builder()
                .title(title)
                .author(author)
                .isbn(isbn)
                .publisher(publisher)
                .publishedDate(LocalDateTime.now())
                .build();

        bookService.save(request);
    }

    private void createAndSaveRequest(String title, String author, String publisher) {
        BookSaveRequest request = BookSaveRequest.builder()
                .title(title)
                .author(author)
                .isbn(UUID.randomUUID().toString())
                .publisher(publisher)
                .publishedDate(LocalDateTime.now())
                .build();

        bookService.save(request);
    }

}