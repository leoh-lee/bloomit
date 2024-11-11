package com.leoh.bloomit.domain.review.dto.response;

public record ReviewResponse(Long id, Long bookId, String bookName, Long memberId, String memberName, String content) {
}
