package com.leoh.bloomit.domain.book.entity.collection;

import com.leoh.bloomit.domain.book.dto.response.BookResponse;
import com.leoh.bloomit.domain.book.entity.Book;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Books {

    private final List<Book> bookList;

    public static Books of(List<Book> books) {
        return new Books(books);
    }

    public List<BookResponse> toBookResponse() {
        if (bookList.isEmpty()) {
            return List.of();
        }

        return bookList.stream()
                .map(BookResponse::fromEntity)
                .toList();
    }
}
