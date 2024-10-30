package com.leoh.bloomit.domain.library.dto.response;

import com.leoh.bloomit.domain.book.dto.response.BookResponse;
import com.leoh.bloomit.domain.member.dto.response.MemberResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LibrarySearchResponse {

    private MemberResponse member;

    private List<BookResponse> books;

    public static LibrarySearchResponse create(MemberResponse member, List<BookResponse> books) {
        return new LibrarySearchResponse(member, books);
    }

}
