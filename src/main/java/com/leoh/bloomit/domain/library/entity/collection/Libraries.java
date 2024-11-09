package com.leoh.bloomit.domain.library.entity.collection;

import com.leoh.bloomit.domain.library.dto.response.LibrarySearchResponse;
import com.leoh.bloomit.domain.library.entity.Library;
import com.leoh.bloomit.domain.member.dto.response.MemberResponse;
import com.leoh.bloomit.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Libraries {

    private final List<Library> libraryList;

    public static Libraries of(List<Library> libraries) {
        if (ObjectUtils.isEmpty(libraries)) {
            throw new IllegalArgumentException("Libraries cannot be empty");
        }
        return new Libraries(libraries);
    }

    public Member getMember() {
        return libraryList.stream()
                .map(Library::getMember)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Member cannot be empty"));
    }

    public List<LibrarySearchResponse> toLibrarySearchResponseList() {
        Member member = getMember();
        MemberResponse memberResponse = MemberResponse.fromEntity(member);

        return this.libraryList.stream()
                .map(library -> LibrarySearchResponse.create(memberResponse, library.getLibraryName()))
                .toList();
    }

}
