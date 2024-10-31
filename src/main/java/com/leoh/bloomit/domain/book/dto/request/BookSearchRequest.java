package com.leoh.bloomit.domain.book.dto.request;

import com.leoh.bloomit.domain.book.enums.BookSearchType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookSearchRequest {

    private BookSearchType bookSearchType;

    private String keyword;

}
