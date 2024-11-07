package com.leoh.bloomit.domain.comment.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentTargetType {


    BOOK("도서"),
    BOOK_REVIEW("도서 리뷰"),
    BOOK_POST("도서 게시글"),
    POST("게시글")
    ;

    private final String description;

}
