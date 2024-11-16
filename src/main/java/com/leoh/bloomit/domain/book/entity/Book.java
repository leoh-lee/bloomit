package com.leoh.bloomit.domain.book.entity;

import com.leoh.bloomit.domain.bookauthor.entity.BookAuthor;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String subTitle;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthors;

    @Column(nullable = false)
    private int pages;

    private String country;

    private String content;

    private String story;

    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private String isbn;

    private String publisher;

    private String publishedDate;

}
