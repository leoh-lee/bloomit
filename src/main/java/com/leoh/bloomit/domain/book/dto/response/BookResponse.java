package com.leoh.bloomit.domain.book.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookResponse(
        Long id,
        String title,
        Long authorId,
        String authorName,
        int pages,
        String country,
        String tableOfContents,
        String story,
        String description,
        String imageUrl,
        String isbn,
        String publisher,
        LocalDate publishedDate
) {
}
