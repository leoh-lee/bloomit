package com.leoh.bloomit.domain.review.dto.request;

import lombok.Builder;

@Builder
public record ReviewCreateRequest(Long bookId, Long memberId, String content) {
}
