package com.leoh.bloomit.domain.author.dto.response;

import com.leoh.bloomit.domain.author.entity.Author;
import lombok.Builder;

@Builder
public record AuthorResponse(String name, String poster, String birth) {

    public static AuthorResponse fromEntity(Author author) {
        return AuthorResponse.builder()
                .name(author.getName())
                .birth(author.getBirth())
                .poster(author.getImageUrl())
                .build();
    }
}
