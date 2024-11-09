package com.leoh.bloomit.domain.library.dto.response;

import com.leoh.bloomit.domain.member.dto.response.MemberResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LibrarySearchResponse {

    private MemberResponse member;

    private String libraryName;

    public static LibrarySearchResponse create(MemberResponse member, String libraryName) {
        return new LibrarySearchResponse(member, libraryName);
    }

}
