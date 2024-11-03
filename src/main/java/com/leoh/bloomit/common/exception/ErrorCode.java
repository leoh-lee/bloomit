package com.leoh.bloomit.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // ResourceNotFoundException
    GENRE_NOT_FOUND("Genre not found"),
    MEMBER_NOT_FOUND("Member not found"),
    ;

    private final String message;

}
