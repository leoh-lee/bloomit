package com.leoh.bloomit.domain.library.entity;

import com.leoh.bloomit.common.entity.BaseEntity;
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
public class Library extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String libraryName;

    @OneToMany(mappedBy = "library", cascade = CascadeType.PERSIST)
    private List<LibraryBook> libraryBooks = new ArrayList<>();

    private Library(Member member, String libraryName) {
        this.member = member;
        this.libraryName = libraryName;
    }

    public static Library create(Member member, String libraryName) {
        return new Library(member, libraryName);
    }

    public List<Book> getBooks() {
        return libraryBooks.stream()
                .map(LibraryBook::getBook)
                .toList();
    }

    public void addBooks(List<Book> books) {
        List<LibraryBook> newLibraryBooks = books.stream()
                .map(book -> LibraryBook.create(this, book))
                .toList();

        libraryBooks.addAll(newLibraryBooks);
    }

}
