package com.leoh.bloomit.domain.bookread.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookReadStatus {

    READING("읽는 중"),
    READ("읽음"),
    UNREAD("읽지 않음")
    ;

    private final String description;

}
