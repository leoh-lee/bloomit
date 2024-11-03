package com.leoh.bloomit.domain.book.service;

import com.leoh.bloomit.domain.book.dto.request.BookSaveRequest;
import com.leoh.bloomit.domain.book.dto.request.BookSearchRequest;
import com.leoh.bloomit.domain.book.dto.response.BookResponse;
import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.book.entity.collection.Books;
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
    public BookResponse createBook(BookSaveRequest request) {
        Book savedBook = bookRepository.save(request.toEntity());
        return BookResponse.fromEntity(savedBook);
    }

    public List<BookResponse> search(BookSearchRequest bookSearchRequest) {
        validateSearchRequest(bookSearchRequest);

        Books findBooks = findBooksByBookSearchType(bookSearchRequest);

        return findBooks.toBookResponse();
    }

    private void validateSearchRequest(BookSearchRequest bookSearchRequest) {
        if (ObjectUtils.isEmpty(bookSearchRequest.getBookSearchType())) {
            throw new IllegalArgumentException("BookSearchType cannot be null or empty");
        }

        if (!StringUtils.hasText(bookSearchRequest.getKeyword())) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }
    }

    private Books findBooksByBookSearchType(BookSearchRequest bookSearchRequest) {
        BookSearchType bookSearchType = bookSearchRequest.getBookSearchType();
        String keyword = bookSearchRequest.getKeyword();

        if (bookSearchType == BookSearchType.TITLE) {
            return Books.of(bookRepository.findByTitleContaining(keyword));
        }

        if (bookSearchType == BookSearchType.ISBN) {
            return Books.of(bookRepository.findByIsbn(keyword));
        }

        if (bookSearchType == BookSearchType.AUTHOR) {
            return Books.of(bookRepository.findByAuthorContaining(keyword));
        }

        return Books.of(bookRepository.findByPublisherContaining(keyword));
    }
}
