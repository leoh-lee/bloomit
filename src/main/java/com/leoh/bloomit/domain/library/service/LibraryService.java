package com.leoh.bloomit.domain.library.service;

import com.leoh.bloomit.domain.book.dto.response.BookResponse;
import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.library.dto.response.LibrarySearchResponse;
import com.leoh.bloomit.domain.library.entity.Library;
import com.leoh.bloomit.domain.library.repository.LibraryRepository;
import com.leoh.bloomit.domain.member.dto.response.MemberResponse;
import com.leoh.bloomit.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibrarySearchResponse findByMemberId(long memberId) {
        Library library = libraryRepository.findByMemberId(memberId);
        Member member = library.getMember();

        MemberResponse memberResponse = MemberResponse.fromEntity(member);

        List<Book> books = library.getBooks();

        List<BookResponse> bookResponses = books.stream()
                .map(BookResponse::fromEntity)
                .toList();

        return LibrarySearchResponse.create(memberResponse, bookResponses);
    }

    public void save(Library library) {
        libraryRepository.save(library);
    }
}
