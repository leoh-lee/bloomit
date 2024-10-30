package com.leoh.bloomit.domain.library.dto.response;

import com.leoh.bloomit.domain.book.dto.response.BookResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class LibrarySearchResponse {

    private List<BookResponse> books;

    private LibrarySearchResponse(List<BookResponse> books) {
        this.books = books;
    }

    public static LibrarySearchResponse create(List<BookResponse> books) {
        return new LibrarySearchResponse(books);
    }

}
