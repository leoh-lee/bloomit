package com.leoh.bloomit.domain.book.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Slf4j
public enum BookSearchType {
    TITLE,
    ISBN,
    AUTHOR,
    PUBLISHER,
    ;

    @JsonCreator
    public static BookSearchType from(String sub) {
        if (!StringUtils.hasText(sub)) {
            throw new IllegalArgumentException("Invalid BookSearchType");
        }

        return Arrays.stream(BookSearchType.values())
                .filter(bookSearchType -> bookSearchType.name().equalsIgnoreCase(sub))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid BookSearchType"));
    }
}
