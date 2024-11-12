package com.leoh.bloomit.domain.book.entity;

import com.leoh.bloomit.domain.author.entity.Author;
import jakarta.persistence.*;
import lombok.*;

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

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @Column(nullable = false)
    private int pages;

    private String country;

    private String tableOfContents;

    private String story;

    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private String isbn;

    private String publisher;

    private String publishedDate;

}
