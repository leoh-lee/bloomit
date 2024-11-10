package com.leoh.bloomit.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // ResourceNotFoundException
    BOOK_NOT_FOUND("Book not found"),
    GENRE_NOT_FOUND("Genre not found"),
    MEMBER_NOT_FOUND("Member not found"),
    AUTHOR_NOT_FOUND("Author not found"),
    ;

    private final String message;

}
