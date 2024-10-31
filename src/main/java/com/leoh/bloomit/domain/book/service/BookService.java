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

        BookSearchType bookSearchType = bookSearchRequest.getBookSearchType();
        String keyword = bookSearchRequest.getKeyword();

        if (ObjectUtils.isEmpty(bookSearchType)) {
            throw new IllegalArgumentException("BookSearchType cannot be null or empty");
        }

        if (!StringUtils.hasText(keyword)) {
            throw new IllegalArgumentException("Keyword cannot be null or empty");
        }

        if (bookSearchType == BookSearchType.TITLE) {
            List<Book> books = bookRepository.findByTitleContaining(keyword);

            return books.stream()
                    .map(BookResponse::fromEntity)
                    .toList();
        }

        if (bookSearchType == BookSearchType.ISBN) {
            List<Book> books = bookRepository.findByIsbn(keyword);

            return books.stream()
                    .map(BookResponse::fromEntity)
                    .toList();
        }

        if (bookSearchType == BookSearchType.AUTHOR) {
            List<Book> books = bookRepository.findByAuthorContaining(keyword);

            return books.stream()
                    .map(BookResponse::fromEntity)
                    .toList();
        }

        if (bookSearchType == BookSearchType.PUBLISHER) {
            List<Book> books = bookRepository.findByPublisherContaining(keyword);

            return books.stream()
                    .map(BookResponse::fromEntity)
                    .toList();
        }

        return List.of();
    }
}
