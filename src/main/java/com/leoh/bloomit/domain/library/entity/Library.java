package com.leoh.bloomit.domain.library.entity;

import com.leoh.bloomit.domain.book.entity.Book;
import com.leoh.bloomit.domain.librarybook.entity.LibraryBook;
import com.leoh.bloomit.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "library")
    private List<LibraryBook> libraryBooks = new ArrayList<>();

    private Library(Member member) {
        this.member = member;
    }

    public static Library create(Member member) {
        return new Library(member);
    }

    public List<Book> getBooks() {
        return libraryBooks.stream()
                .map(LibraryBook::getBook)
                .toList();
    }
}
