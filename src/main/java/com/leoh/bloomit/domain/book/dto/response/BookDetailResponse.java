package com.leoh.bloomit.domain.book.dto.response;

import com.leoh.bloomit.domain.author.dto.response.AuthorResponse;
import com.leoh.bloomit.domain.review.dto.response.ReviewResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record BookDetailResponse(
         Long bookId,
         String title,
         String subTitle,
         Integer pages,
         String publishedYear,
         String posterUrl,
         BigDecimal ratingAvg,
         Integer ratingCount,
         String description,
         String content,

         List<ReviewResponse> reviews,
         List<AuthorResponse> authors
) {
}
