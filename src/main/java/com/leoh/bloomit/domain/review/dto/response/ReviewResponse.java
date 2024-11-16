package com.leoh.bloomit.domain.review.dto.response;

import com.leoh.bloomit.domain.review.entity.Review;
import lombok.Builder;

@Builder
public record ReviewResponse(Long id, Long bookId, String bookTitle, Long memberId, String memberName, String content) {

    public static ReviewResponse fromEntity(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .bookId(review.getBookId())
                .bookTitle(review.getBookTitle())
                .content(review.getContent())
                .memberId(review.getMemberId())
                .memberName(review.getMemberName())
                .build();
    }
}
