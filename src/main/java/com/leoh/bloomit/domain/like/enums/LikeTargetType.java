package com.leoh.bloomit.domain.like.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LikeTargetType {

    BOOK("도서"),
    AUTHOR("작가"),
    BOOK_REVIEW("도서 리뷰"),
    BOOK_REVIEW_COMMENT("도서 리뷰 댓글"),
    BOOK_POST("도서 게시글"),
    BOOK_POST_COMMENT("도서 게시글 댓글"),
    POST("게시글"),
    POST_COMMENT("게시글 댓글")
    ;

    private final String description;

}
