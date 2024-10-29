package com.leoh.bloomit.domain.librarybook.entity;

import com.leoh.bloomit.common.entity.BaseEntity;
import com.leoh.bloomit.domain.library.entity.Library;
import com.leoh.bloomit.domain.book.entity.Book;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryBook extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    private Library library;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

}
