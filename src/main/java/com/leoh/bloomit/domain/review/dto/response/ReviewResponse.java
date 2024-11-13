package com.leoh.bloomit.domain.review.dto.response;

import com.leoh.bloomit.domain.review.entity.Review;
import lombok.Builder;

@Builder
public record ReviewResponse(Long id, Long bookId, String bookName, Long memberId, String memberName, String content) {

    public static ReviewResponse fromEntity(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .bookId(review.getBook().getId())
                .bookName(review.getBook().getTitle())
                .content(review.getContent())
                .memberId(review.getWriter().getId())
                .memberName(review.getWriter().getName())
                .build();
    }
}
