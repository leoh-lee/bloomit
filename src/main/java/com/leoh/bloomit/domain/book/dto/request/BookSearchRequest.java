package com.leoh.bloomit.domain.book.dto.request;

import com.leoh.bloomit.domain.book.enums.BookSearchType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BookSearchRequest {

    private BookSearchType bookSearchType;

    private String keyword;

}
