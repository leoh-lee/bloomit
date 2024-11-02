package com.leoh.bloomit.domain.genre.dto.response;

import com.leoh.bloomit.domain.genre.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreResponse {

    private Long id;
    private String name;

    public static GenreResponse from(Genre genre) {
        return new GenreResponse(genre.getId(), genre.getName());
    }

}
