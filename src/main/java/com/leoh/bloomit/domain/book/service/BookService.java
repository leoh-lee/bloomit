package com.leoh.bloomit.domain.book.service;

import com.leoh.bloomit.domain.book.dto.request.BookSaveRequest;
import com.leoh.bloomit.domain.book.dto.request.BookSearchRequest;
import com.leoh.bloomit.domain.book.dto.response.BookResponse;
import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.enums.BookSearchType;
import com.leoh.bloomit.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void save(BookSaveRequest request) {
        bookRepository.save(request.toEntity());
    }

    public List<BookResponse> search(BookSearchRequest bookSearchRequest) {

        validateSearchRequest(bookSearchRequest);

        List<Book> findBooks = findBooksByBookSearchType(bookSearchRequest);

        return convertBooksToBookResponses(findBooks);
    }

    private void validateSearchRequest(BookSearchRequest bookSearchRequest) {
        if (ObjectUtils.isEmpty(bookSearchRequest.getBookSearchType())) {
            throw new IllegalArgumentException("BookSearchType cannot be null or empty");
        }

        if (!StringUtils.hasText(bookSearchRequest.getKeyword())) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }
    }

    private List<BookResponse> convertBooksToBookResponses(List<Book> findBooks) {
        if (findBooks.isEmpty()) {
            return List.of();
        }

        return findBooks.stream()
                .map(BookResponse::fromEntity)
                .toList();
    }

    private List<Book> findBooksByBookSearchType(BookSearchRequest bookSearchRequest) {
        BookSearchType bookSearchType = bookSearchRequest.getBookSearchType();
        String keyword = bookSearchRequest.getKeyword();

        if (bookSearchType == BookSearchType.TITLE) {
            return bookRepository.findByTitleContaining(keyword);
        }

        if (bookSearchType == BookSearchType.ISBN) {
            return bookRepository.findByIsbn(keyword);
        }

        if (bookSearchType == BookSearchType.AUTHOR) {
            return bookRepository.findByAuthorContaining(keyword);
        }

        return bookRepository.findByPublisherContaining(keyword);
    }
}